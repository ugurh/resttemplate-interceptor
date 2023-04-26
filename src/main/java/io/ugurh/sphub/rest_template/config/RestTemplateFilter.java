package io.ugurh.sphub.rest_template.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author harun ugur
 * @project rest-template-interceptor
 * @created 13.04.2023 - 22:26
 */
public class RestTemplateFilter implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateFilter.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        LOGGER.info("RestTemplate does a http/s to - {} with HTTP Method : {}", request.getURI(), request.getMethod());

        ClientHttpResponse response = execution.execute(request, body);

        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            String bodyStr = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);

            LOGGER.error("RestTemplate received a bad response from : {} - with response status : {} and body : {} ",
                    request.getURI(),
                    response.getStatusCode(),
                    bodyStr);
        } else {
            LOGGER.info("RestTemplate received a good response from : {}- with response status {}",
                    request.getURI(),
                    response.getStatusCode());
        }

        return response;
    }
}
