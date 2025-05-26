package br.com.executor.api_executor.model;

import java.util.List;
import java.util.Map;

public record ConfigData(List<Service> services, DefaultData defaultData) {

    public record Service(
            String key,
            String baseUrl,
            String uri,
            String method,
            Map<String, String> body,
            Map<String, String> headers,
            Map<String, String> queryParams,
            Map<String, String> params,
            int timeout,
            int retries
    ) {
    }

    public record DefaultData(
            int timeout,
            int retries
    ) {
    }
}
