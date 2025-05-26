package br.com.executor.api_executor.clients;

import br.com.executor.api_executor.model.GlobalPayloadRequest;
import br.com.executor.api_executor.model.GlobalPayloadResponse;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public class AwsLambdaClientExecutor implements ClientExecutor {

    // Implement the methods required for AWS Lambda client execution
    @Override
    public Optional<GlobalPayloadResponse> execute(GlobalPayloadRequest globalPayloadRequest) throws Exception {
        // Logic to execute the request against AWS Lambda
        // This is a placeholder implementation
        return Optional.empty();
    }

}
