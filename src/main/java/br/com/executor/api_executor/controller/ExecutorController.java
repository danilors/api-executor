package br.com.executor.api_executor.controller;

import br.com.executor.api_executor.service.ApiExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/executor")
public class ExecutorController {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorController.class);

    private final ApiExecutorService apiExecutorService;

    public ExecutorController(ApiExecutorService apiExecutorService) {
        this.apiExecutorService = apiExecutorService;
    }

    @PostMapping(value = "/execute", produces = "application/json")
    public Mono<?> execute(@RequestBody Map<String, Object> payload,
                           @RequestHeader Map<String, String> headers) {
        MDC.put("requestId", String.valueOf(System.currentTimeMillis()));
        logger.info("Received request with headers: {}", headers);
        logger.info("Received payload: {}", payload);

        return Mono.just(apiExecutorService.execute(payload, headers));
    }

}