package com.example.process_service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProcessServiceApplication {
    @Bean
    MyHost myBean() {
        return new MyHost();
    }

    public static void main(String[] args) {

        String gatewayPort = "8081/register";
        String gatewayHost = "http://192.168.103.226:";

        ApplicationContext context = SpringApplication.run(ProcessServiceApplication.class, args);

        MyHost myBean = context.getBean(MyHost.class);


        HttpHeaders headers = new HttpHeaders();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("host", myBean.getMyHost()).put("port", Integer.parseInt(myBean.getMyPort()));
        HttpEntity<Object> entity = new HttpEntity<>(jsonObject.toString(), headers);
        System.out.println(restTemplate.exchange(gatewayHost + gatewayPort, HttpMethod.POST, entity, Object.class));

    }

    private static class MyHost {
        @Value("${service-host}")
        public String myHost;
        @Value("${service-port}")
        public String myPort;

        public String getMyHost() {
            return myHost;
        }

        public String getMyPort() {
            return myPort;
        }
    }

}
