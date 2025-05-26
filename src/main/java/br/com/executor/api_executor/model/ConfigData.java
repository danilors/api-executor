package br.com.executor.api_executor.model;

import java.util.List;
import java.util.Map;

public record ConfigData(List<Data> data, DefaultData defaultData) {

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

    public record DefaultData (
            int timeout,
            int retries
    ) {
    }
}
