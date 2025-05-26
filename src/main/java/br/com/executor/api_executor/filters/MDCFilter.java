package br.com.executor.api_executor.filters;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class MDCFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);

        String userId = exchange.getRequest().getHeaders().getFirst("X-User-Id");
        if (userId == null || userId.isEmpty()) {
            userId = "anonymous";
        }
        MDC.put("userId", userId);

        return chain.filter(exchange)
                .doFinally(signalType -> MDC.clear());
    }
}