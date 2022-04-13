package com.trongit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TrongITApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TrongITApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TrongITApplication.class, args);
    }

    //video
}
