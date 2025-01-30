package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class LambdaHandler extends SpringBootRequestHandler<Object, Object> implements RequestHandler<Object, Object> {

    @Override
    public Object handleRequest(Object input, Context context) {
        // Call the function defined in your Spring Boot application
        return super.handleRequest(input, context);
    }
}
