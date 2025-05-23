package com.example.baiduapistoreconnector;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Baidu API Store Connector - Java",
                                version = "1.0.0",
                                description = "A Java Spring Boot application that connects to the Baidu API store, providing RESTful APIs for weather, AQI, and other services."))
public class BaiduApiStoreConnectorJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaiduApiStoreConnectorJavaApplication.class, args);
    }

}
