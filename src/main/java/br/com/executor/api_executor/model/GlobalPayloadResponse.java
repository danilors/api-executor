package br.com.executor.api_executor.model;

import java.util.List;
import java.util.Map;

public record GlobalPayloadResponse(
        Map<String, Object> data,
        Map<String, String> errors
) {

}
