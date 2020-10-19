package com.example.process_service.services;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class RestService {

    private final RestTemplate restTemplate;

    public final String userServiceUrl;
    public final String insuranceServiceUrl;

    @Autowired
    public RestService(RestTemplateBuilder restTemplateBuilder, @Value("${user-service-url}") String userServiceUrl, @Value("${user-service-url}") String insuranceServiceUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.userServiceUrl = userServiceUrl;
        this.insuranceServiceUrl = insuranceServiceUrl;
    }

    public Object getUsers(String priority) {
        String url = this.userServiceUrl + "/users";
        HttpHeaders headers = new HttpHeaders();
        headers.add("priority", priority);
        HttpEntity<Object> entity = new HttpEntity<Object>("", headers);
        return this.restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
    }


    public Object getStatus() {
        String url = this.insuranceServiceUrl + "/working";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<Object>("", headers);
        return this.restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
    }


    public Object getInsurancesByUserId(String id) {
        String url = this.userServiceUrl + "/users/" + id + "/insurances";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<Object>("", headers);
        return this.restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
    }

    public Object createInsurance(String userId, Map<String, String> body) {
        String url = this.userServiceUrl + "/insurance/create";
        HttpHeaders headers = new HttpHeaders();

        String price = body.get("price");
        String name = body.get("name");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", userId);
        HttpEntity<Object> entity = new HttpEntity<>(jsonObject.toString(), headers);

        this.restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);

//   jsonObject.put();

        return this.restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
    }


    public Object getUserForId(int id, String priority) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("priority", priority);
        HttpEntity<Object> entity = new HttpEntity<Object>("", headers);
        String url = userServiceUrl + "users/" + id;
        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (response.getBody() == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return this.restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
        }
    }

    public Object createUser(Object object, String priority) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("priority", priority);
        String url = userServiceUrl + "/users";
        HttpEntity<Object> entity = new HttpEntity<>(object, headers);
        return this.restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
    }

    public Object updateUser(Object object, String id, String priority) {
        String url = userServiceUrl + "/users/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.add("priority", priority);
        HttpEntity<Object> entity = new HttpEntity<>(object, headers);
        return this.restTemplate.exchange(url, HttpMethod.PUT, entity, Object.class);
    }

    public void deleteUser(String id, String priority) {
        String url = userServiceUrl + "/users/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.add("priority", priority);
        HttpEntity<Object> entity = new HttpEntity<>("", headers);
        this.restTemplate.exchange(url, HttpMethod.DELETE, entity, Object.class);
    }


}