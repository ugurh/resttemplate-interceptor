package io.ugurh.resttemplateinterceptor.model;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 26.04.2023 - 15:14
 */
@Component
public class ResponseBuilder {

    public ResponseEntity <ApiResponse> buildResponse(HttpHeaders httpHeader, int httpStatusCode, String message, Object data, Map <String, Object> otherParams) {
        return new ApiResponse.ApiResponseBuilder <> (httpStatusCode, message).withHttpHeader(httpHeader)
                .withData(data)
                .withOtherParams(otherParams)
                .build();
    }

    public ResponseEntity <ApiResponse> buildResponse(int httpStatusCode, String message, Object data, Map <String, Object> otherParams) {
        return new ApiResponse.ApiResponseBuilder <> (httpStatusCode, message)
                .withData(data).withOtherParams(otherParams)
                .build();
    }

    public ResponseEntity <ApiResponse> buildResponse(int httpStatusCode, String message, Map <String, Object> otherParams) {
        return new ApiResponse.ApiResponseBuilder <> (httpStatusCode, message)
                .withOtherParams(otherParams)
                .build();
    }

    public ResponseEntity <ApiResponse> buildResponse(HttpHeaders httpHeader, int httpStatusCode, String message, Object data) {
        return new ApiResponse.ApiResponseBuilder <> (httpStatusCode, message)
                .withHttpHeader(httpHeader)
                .withData(data)
                .build();
    }

    public ResponseEntity <ApiResponse> buildResponse(HttpHeaders httpHeader, int httpStatusCode, String message, Map<String, Object> otherParams) {
        return new ApiResponse.ApiResponseBuilder <> (httpStatusCode, message)
                .withHttpHeader(httpHeader)
                .withOtherParams(otherParams)
                .build();
    }

    public ResponseEntity <ApiResponse> buildResponse(HttpHeaders httpHeader, int httpStatusCode, String message) {
        return new ApiResponse.ApiResponseBuilder <> (httpStatusCode, message)
                .withHttpHeader(httpHeader)
                .build();
    }

    public ResponseEntity<ApiResponse> buildResponse(int httpStatusCode, String message, Object data) {
        return new ApiResponse.ApiResponseBuilder <> (httpStatusCode, message)
                .withData(data)
                .build();
    }

}
