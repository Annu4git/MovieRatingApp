package org.anurag.microservice_configurations.controller;

import org.anurag.microservice_configurations.config.DBSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/greetings")
@RefreshScope
public class GreetingsController {

    @Value("${greetings.message}")
    private String greetingsMessage;

    @Value("this is direct value mapping")
    private String directValue;

    @Value("${bookname: book name not found, printing default message}")
    String bookname;

    @Value("${books}")
    List<String> books;

    @Value("#{${students}}")
    Map<String, String> students;

    @Autowired
    DBSettings db;

    @Value("${employee.name}")
    String empName;

    @Autowired
    Environment env;

    @GetMapping("/message")
    public String message() {
        return greetingsMessage;
    }
}











