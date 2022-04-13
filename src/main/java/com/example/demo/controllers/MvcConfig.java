package com.example.demo.controllers;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/word").setViewName("word");
        registry.addViewController("/words").setViewName("words");
        registry.addViewController("/settings").setViewName("settings");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/createPoll").setViewName("createPoll");
    }

}