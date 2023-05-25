package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;


public class LambdaHash implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        try {
            String inputString = input.getQueryStringParameters().get("input");

            if (inputString == null || inputString.length() < 8) {
                return createErrorResponse(400, "Input string should have at least 8 characters.", gson);
            }

            if (!inputString.matches(".*\\d.*")) {
                return createErrorResponse(400, "Input string should contain at least one number.", gson);
            }

            if (!inputString.matches(".*[!@#$%^&*(){},.?:|<>].*")) {
                return createErrorResponse(400, "Input string should contain at least one special character.", gson);
            }

            String hash = getSHA256Hash(inputString);

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("hash", hash);

            response.setStatusCode(200);
            response.setBody(gson.toJson(responseBody));

        } catch (Exception e) {
            return createErrorResponse(500, "Internal Server Error", gson);
        }

        return response;
    }

    private String getSHA256Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(encodedHash).toLowerCase();
    }

    private APIGatewayProxyResponseEvent createErrorResponse(int statusCode, String message, Object payload) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(statusCode);
        response.setBody(gson.toJson(payload));
        return response;
    }
}
