package br.com.executor.api_executor.clients;

import br.com.executor.api_executor.converter.MapConverter;
import br.com.executor.api_executor.model.ConfigData;
import br.com.executor.api_executor.model.GlobalPayloadRequest;
import br.com.executor.api_executor.model.GlobalPayloadResponse;
import br.com.executor.api_executor.service.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.*;

/**
 * The {@code HttpClientExecutor} class is responsible for executing HTTP requests
 * to multiple services defined in the configuration. It uses Spring WebFlux's
 * {@code WebClient} to perform asynchronous and reactive HTTP calls.
 *
 * <p>This class implements the {@code ClientExecutor} interface and provides
 * the functionality to:
 * <ul>
 *   <li>Retrieve service configurations from the {@code ConfigurationService}.</li>
 *   <li>Execute HTTP requests for each service with support for timeouts and retries.</li>
 *   <li>Collect successful responses and errors into separate maps.</li>
 * </ul>
 *
 * <p>Each service's response is added to the results map, while any errors
 * encountered during execution are added to the errors map. The results and
 * errors are returned as part of a {@code GlobalPayloadResponse}.
 *
 * <p>Supported HTTP methods for requests with a body include POST, PUT, PATCH, and DELETE.
 *
 * @author danilors
 * @see ClientExecutor
 * @see ConfigurationService
 * @see WebClient
 */
@Component
public class HttpClientExecutor implements ClientExecutor {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientExecutor.class);

    private final ConfigurationService configurationService;
    private final WebClient.Builder webClientBuilder;

    public HttpClientExecutor(
            WebClient.Builder webClientBuilder,
            ConfigurationService configurationService
    ) {
        this.configurationService = configurationService;
        this.webClientBuilder = webClientBuilder;
    }

/**
 * Executes HTTP requests for multiple services defined in the configuration.
 *
 * <p>This method retrieves the service configurations from the {@code ConfigurationService},
 * performs HTTP requests for each service using {@code WebClient}, and collects the results
 * and errors into separate maps. The results and errors are returned as part of a
 * {@code GlobalPayloadResponse}.
 *
 * <p>For each service:
 * <ul>
 *   <li>The HTTP request is configured with the service's base URL, URI, headers, query parameters, and body.</li>
 *   <li>Timeout and retry configurations are applied based on the service's settings.</li>
 *   <li>Successful responses are added to the results map, keyed by the service's unique key.</li>
 *   <li>Errors encountered during execution are added to the errors map, keyed by the service's unique key.</li>
 * </ul>
 *
 * @param request The {@code GlobalPayloadRequest} containing the request details.
 * @return An {@code Optional} containing the {@code GlobalPayloadResponse} with the results and errors,
 *         or an empty {@code Optional} if no services are found in the configuration.
 */
    @Override
    public Optional<GlobalPayloadResponse> execute(GlobalPayloadRequest request) {
        Optional<ConfigData> config = configurationService.getConfiguration("services");

        if (config.isPresent()) {
            List<ConfigData.Service> services = config.get().services();

            if (services.isEmpty()) {
                logger.warn("No services found in configuration");
                return Optional.empty();
            }

            Map<String, Object> results = new HashMap<>();
            Map<String, String> errors = new HashMap<>();

            Flux.fromIterable(services)
                    .flatMap(service -> {
                        WebClient webClient = webClientBuilder.baseUrl(service.baseUrl()).build();

                        WebClient.RequestHeadersSpec<?> requestSpec = webClient.method(HttpMethod.valueOf(service.method()))
                                .uri(uriBuilder -> uriBuilder
                                        .path(service.uri())
                                        .queryParams(MapConverter.convertToMultiValueMap(service.queryParams()))
                                        .build(MapConverter.convertToMultiValueMap(service.params())))
                                .headers(headers -> headers.setAll(service.headers()))
                                .contentType(MediaType.APPLICATION_JSON);

                        // Include body only for methods that support it
                        if (supportsBody(service.method()) && service.body() != null) {
                            requestSpec = ((WebClient.RequestBodySpec) requestSpec).bodyValue(service.body());
                        }

                        return requestSpec.retrieve()
                                .bodyToMono(Map.class)
                                .doOnNext(response -> results.put(service.key(), response)) // Add to results
                                .doOnError(error -> {
                                    logger.error("Error executing service: {}", service.key(), error);
                                    errors.put(service.key(), error.getMessage()); // Add to errors
                                })
                                .timeout(Duration.ofMillis(service.timeout())) // Apply timeout
                                .retry(service.retries()); // Apply retries
                    })
                    .collectList().block();

            return Optional.of(new GlobalPayloadResponse(results, errors));
        }

        logger.warn("No services found in configuration");
        return Optional.empty();
    }

    private boolean supportsBody(String method) {
        return "POST".equalsIgnoreCase(method) ||
                "PUT".equalsIgnoreCase(method) ||
                "PATCH".equalsIgnoreCase(method) ||
                "DELETE".equalsIgnoreCase(method);
    }
}