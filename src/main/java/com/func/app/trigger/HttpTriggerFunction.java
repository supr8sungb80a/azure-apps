package com.func.app;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

public class HttpTriggerFunction {

    @FunctionName("HttpTriggerJava")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        String name = request.getQueryParameters().get("name");

        // Parse request body
        String requestBody = request.getBody().orElse(null);

        if (name == null && requestBody != null) {
            name = requestBody;
        }

        if (name == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Please pass a name on the query string or in the request body")
                    .build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK)
                    .body("Hello, " + name + "!")
                    .build();
        }
    }
}

