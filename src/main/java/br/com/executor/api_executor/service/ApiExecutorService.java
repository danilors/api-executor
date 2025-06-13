package br.com.executor.api_executor.service;

import br.com.executor.api_executor.clients.HttpClientExecutor;
import br.com.executor.api_executor.model.GlobalPayloadRequest;
import br.com.executor.api_executor.model.GlobalPayloadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ApiExecutorService {

    private static final Logger logger = LoggerFactory.getLogger(ApiExecutorService.class);

    private final HttpClientExecutor httpClientExecutor;


    public ApiExecutorService(HttpClientExecutor httpClientExecutor) {
        this.httpClientExecutor = httpClientExecutor;
    }

    public GlobalPayloadResponse execute(Map<String, Object> payload, Map<String, String> headers) {
        logger.info("Executing APIs with payload: {}", payload);

        Optional<GlobalPayloadResponse> response = httpClientExecutor.execute(new GlobalPayloadRequest(payload));

        return response
                .orElseGet(() -> {
                    logger.warn("No response received from API execution");
                    return new GlobalPayloadResponse(Map.of(), Map.of("error", "No response received"));
                });
    }

}
