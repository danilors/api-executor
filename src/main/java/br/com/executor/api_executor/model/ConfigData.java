package br.com.executor.api_executor.model;

import java.util.List;
import java.util.Map;

public record ConfigData(List<Data> data) {

    public record Data(
            String key,
            String url,
            String method,
            Map<String, String> body,
            Map<String, String> headers,
            Map<String, String> queryParams,
            Map<String, String> params
    ) {
    }
}
