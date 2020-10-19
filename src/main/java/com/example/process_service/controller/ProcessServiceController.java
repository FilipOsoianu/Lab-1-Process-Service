package com.example.process_service.controller;

import com.example.process_service.services.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ProcessServiceController {

    @Value("${user-service-url}")
    private String userServiceApi;

    @Value("${insurance-service-url}")
    private String insuranceServiceApi;

    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

    @Autowired
    RestService restService = new RestService(restTemplateBuilder, userServiceApi, insuranceServiceApi);

    @GetMapping("/users/{id}")
    public Object show(@PathVariable String id, @RequestHeader("priority") String priority) {
        int userId = Integer.parseInt(id);
        return restService.getUserForId(userId, priority);
    }

    @GetMapping("/user/{user_id}/insurances")
    public Object getInsuranceFroId(@PathVariable String user_id) {

        return restService.getInsurancesByUserId(user_id);
    }

    @GetMapping("/status")
    public Object getStatus() {

        return restService.getStatus();
    }

    @PostMapping("/user/{user_id}/insurances")
    public Object createInsurance(@PathVariable String user_id, @RequestBody Map<String, String> body) {

        return restService.createInsurance(user_id, body);
    }

    @PostMapping("/users")
    public Object createUser(@RequestBody Map<String, String> body, @RequestHeader("priority") String priority) {
        return restService.createUser(body, priority);
    }

    @PutMapping("/users/{id}")
    public Object updateUser(@PathVariable String id, @RequestBody Map<String, String> body, @RequestHeader("priority") String priority) {
        return restService.updateUser(body, id, priority);
    }

    @GetMapping("/users")
    public Object show(@RequestHeader("priority") String priority) {
        return restService.getUsers(priority);
    }


    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable String id, @RequestHeader("priority") String priority) {
        restService.deleteUser(id, priority);
    }

}
