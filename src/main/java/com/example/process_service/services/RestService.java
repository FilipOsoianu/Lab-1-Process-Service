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

    public Object getUsers(String priority) {
        String url = "http://localhost:8080/user";
        HttpHeaders headers = new HttpHeaders();
        headers.add("priority", priority);
        HttpEntity<Object> entity = new HttpEntity<Object>("", headers);
        return this.restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
    }

    public Object getUserForId(int id, String priority) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("priority", priority);
        HttpEntity<Object> entity = new HttpEntity<Object>("", headers);
        String url = "http://localhost:8080/user/" + id;
        return this.restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
    }

    public Object createUser(Object object, String priority) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("priority", priority);
        String url = "http://localhost:8080/user";
        HttpEntity<Object> entity = new HttpEntity<>(object, headers);
        return this.restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
    }

    public Object updateUser(Object object, String id, String priority) {
        String url = "http://localhost:8080/user/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.add("priority", priority);
        HttpEntity<Object> entity = new HttpEntity<>(object, headers);
        return this.restTemplate.exchange(url, HttpMethod.PUT, entity, Object.class);
    }

    public void deleteUser(String id, String priority) {
        String url = "http://localhost:8080/user/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.add("priority", priority);
        HttpEntity<Object> entity = new HttpEntity<>("", headers);
        this.restTemplate.exchange(url, HttpMethod.DELETE, entity, Object.class);
    }


}