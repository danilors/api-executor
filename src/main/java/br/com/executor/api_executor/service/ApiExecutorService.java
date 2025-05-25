package br.com.executor.api_executor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ApiExecutorService {

    private static final Logger logger = LoggerFactory.getLogger(ApiExecutorService.class);

    public Map<String, Object> execute(Map<String, Object> payload, Map<String, String> headers) {
        logger.info("Executing APIs with payload: {}", payload);

        return Map.of("status", "success", "payload", payload);
    }

}
