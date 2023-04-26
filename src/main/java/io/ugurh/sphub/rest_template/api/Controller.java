package io.ugurh.sphub.rest_template.api;

import io.ugurh.sphub.rest_template.model.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author harun ugur
 * @project rest-template-interceptor
 * @created 13.04.2023 - 22:02
 */

@RestController
@RequestMapping("/api")
public class Controller {

    private final RestTemplate restTemplate;

    public Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/messages")
    public ResponseEntity<Resource> hello() {
        return ResponseEntity.status(HttpStatus.OK).body(new Resource("Spring Boot"));
    }

    @GetMapping("/resources")
    public ResponseEntity<String> getResource() {
        RequestEntity<Void> request = RequestEntity
                .get(URI.create("http://localhost:8081/api/messages"))
                .accept(MediaType.APPLICATION_JSON)
                .build();


        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
    }
}
