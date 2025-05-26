package br.com.executor.api_executor.clients;

import br.com.executor.api_executor.model.GlobalPayloadRequest;
import br.com.executor.api_executor.model.GlobalPayloadResponse;

import java.util.Optional;

public interface ClientExecutor {
    Optional<GlobalPayloadResponse> execute(GlobalPayloadRequest globalPayloadRequest) throws Exception;
}
