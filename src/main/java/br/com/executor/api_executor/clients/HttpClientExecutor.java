package br.com.executor.api_executor.clients;

import br.com.executor.api_executor.converter.MapConverter;
import br.com.executor.api_executor.model.ConfigData;
import br.com.executor.api_executor.model.GlobalPayloadRequest;
import br.com.executor.api_executor.model.GlobalPayloadResponse;
import br.com.executor.api_executor.service.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;

/**
 * The {@code HttpClientExecutor} class is responsible for executing HTTP requests
 * to multiple services defined in the configuration. It uses Spring WebFlux's
 * {@code WebClient} to perform asynchronous and reactive HTTP calls.
 */
@Component
public class HttpClientExecutor implements ClientExecutor {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientExecutor.class);

    private final ConfigurationService configurationService;
    private final WebClient.Builder webClientBuilder;

    public HttpClientExecutor(WebClient.Builder webClientBuilder, ConfigurationService configurationService) {
        this.configurationService = configurationService;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Optional<GlobalPayloadResponse> execute(GlobalPayloadRequest request) {
        Optional<ConfigData> config = configurationService.getConfiguration("services");

        if (config.isEmpty()) {
            logger.warn("No services found in configuration");
            return Optional.empty();
        }

        ConfigData configData = config.get();
        List<ConfigData.Service> services = configData.services();

        if (CollectionUtils.isEmpty(services)) {
            logger.warn("No services found in configuration");
            return Optional.empty();
        }

        Map<String, Object> results = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        Flux.fromIterable(services)
                .flatMap(service -> executeService(service, configData, results, errors))
                .collectList()
                .block();

        return Optional.of(new GlobalPayloadResponse(results, errors));
    }

private Mono<Map<String, Object>> executeService(ConfigData.Service service, ConfigData configData,
                                                 Map<String, Object> results, Map<String, String> errors) {
    WebClient webClient = webClientBuilder.baseUrl(service.baseUrl()).build();

    WebClient.RequestHeadersSpec<?> requestSpec = buildRequestSpec(webClient, service);

    int timeout = service.timeout() > 0 ? service.timeout() : configData.defaultData().timeout();
    int retries = service.retries() > 0 ? service.retries() : configData.defaultData().retries();

    return requestSpec.retrieve()
            .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
            .timeout(Duration.ofMillis(timeout))
            .retry(retries)
            .doOnNext(response -> {
                logger.info("Service {} executed successfully", service.key());
                results.put(service.key(), response);
            })
            .onErrorResume(error -> {
                logger.error("Error executing service {}: {}", service.key(), error.getMessage());
                errors.put(service.key(), error.getMessage());
                return Mono.empty();
            });
}
    private WebClient.RequestHeadersSpec<?> buildRequestSpec(WebClient webClient, ConfigData.Service service) {
        WebClient.RequestHeadersSpec<?> requestSpec = webClient.method(HttpMethod.valueOf(service.method()))
                .uri(uriBuilder -> uriBuilder
                        .path(service.uri())
                        .queryParams(MapConverter.convertToMultiValueMap(service.queryParams()))
                        .build(MapConverter.convertToMultiValueMap(service.params())))
                .headers(headers -> headers.setAll(service.headers()))
                .contentType(MediaType.APPLICATION_JSON);

        if (supportsBody(service.method()) && service.body() != null) {
            requestSpec = ((WebClient.RequestBodySpec) requestSpec).bodyValue(service.body());
        }

        return requestSpec;
    }

    private boolean supportsBody(String method) {
        return "POST".equalsIgnoreCase(method) ||
                "PUT".equalsIgnoreCase(method) ||
                "PATCH".equalsIgnoreCase(method) ||
                "DELETE".equalsIgnoreCase(method);
    }
}