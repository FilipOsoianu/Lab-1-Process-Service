package com.example.process_service.controller;

import com.example.process_service.services.RestService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;


@RestController
public class ProcessServiceController {

    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
    RestService restService = new RestService(restTemplateBuilder);


    @GetMapping("/user/{id}")
    public Object show(@PathVariable String id, @RequestHeader("priority") String priority) {
        int userId = Integer.parseInt(id);
        return restService.getUserForId(userId, priority);
    }

    @PostMapping("/user")
    public Object createUser(@RequestBody Map<String, String> body, @RequestHeader("priority") String priority) {
        return restService.createUser(body, priority);
    }

    @PutMapping("/user/{id}")
    public Object updateUser(@PathVariable String id, @RequestBody Map<String, String> body, @RequestHeader("priority") String priority) {
        return restService.updateUser(body, id, priority);
    }

    @GetMapping("/user")
    public Object show(@RequestHeader("priority") String priority) {
        return restService.getUsers(priority);
    }


    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable String id, @RequestHeader("priority") String priority) {
        restService.deleteUser(id, priority);
    }

}
