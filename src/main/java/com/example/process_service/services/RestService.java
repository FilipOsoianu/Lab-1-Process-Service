package com.example.process_service.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Object getUserForId(int id) {
        String url = "http://localhost:8080/user/" + id;
        return this.restTemplate.getForObject(url, Object.class);
    }

    public Object createUser(Object object) {
        String url = "http://localhost:8080/user";
        return this.restTemplate.postForObject(url, object, Object.class);
    }

    public Object updateUser(Object object, String id) {
        String url = "http://localhost:8080/user/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(object, headers);
        return this.restTemplate.exchange(url, HttpMethod.PUT, entity, Object.class);
    }

    public void deleteUser(String id) {
        String url = "http://localhost:8080/user/" + id;
        this.restTemplate.delete(url);
    }

    public Object getUsers() {
        String url = "http://localhost:8080/user";
        return this.restTemplate.getForObject(url, Object.class);
    }


}