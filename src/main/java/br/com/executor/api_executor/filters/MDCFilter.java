package br.com.executor.api_executor.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class MDCFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // Gerar um Request ID único para cada requisição
            String requestId = UUID.randomUUID().toString();
            MDC.put("requestId", requestId);

            // Tentar obter o ID do usuário, se disponível (ex: de um token JWT, sessão, etc.)
            // Este é um exemplo simples. Em uma aplicação real, você teria um contexto de segurança.
            String userId = request.getHeader("X-User-Id"); // Exemplo: um header customizado
            if (userId == null || userId.isEmpty()) {
                userId = "anonymous";
            }
            MDC.put("userId", userId);

            // Continuar a cadeia de filtros
            filterChain.doFilter(request, response);
        } finally {
            // É CRÍTICO limpar o MDC ao final da requisição para evitar vazamento de dados
            // entre requisições ou threads reusadas por um pool de threads.
            MDC.clear();
        }
    }
}