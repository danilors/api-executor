package br.com.executor.api_executor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/executor")
public class ExecutorController {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorController.class);

    @PostMapping("/execute")
    public Mono<?> execute(@RequestBody Map<String, Object> payload) {
        MDC.put("requestId", String.valueOf(System.currentTimeMillis()));

        logger.info("Received payload: {}", payload);
        return Mono.just(payload);
    }

}