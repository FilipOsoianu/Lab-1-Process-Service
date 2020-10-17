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
    public Object show(@PathVariable String id) {
        int userId = Integer.parseInt(id);
        return restService.getUserForId(userId);
    }

    @PostMapping("/user")
    public Object createUser(@RequestBody Map<String, String> body) {
        return restService.createUser(body);
    }

    @PutMapping("/user/{id}")
    public Object updateUser(@PathVariable String id, @RequestBody Map<String, String> body) throws ParseException {
        return restService.updateUser(body, id);
    }

    @GetMapping("/user")
    public Object show() {
        return restService.getUsers();
    }


    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable String id) {
        restService.deleteUser(id);
    }

}
