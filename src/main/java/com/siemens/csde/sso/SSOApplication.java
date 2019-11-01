package com.siemens.csde.sso;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@ServletComponentScan
@EnableJpaRepositories("com.siemens.csde.sso.jpa.repository")
@EntityScan("com.siemens.csde.sso.jpa.entity")
@ComponentScan(basePackages = {"com.siemens"})
public class SSOApplication extends SpringBootServletInitializer implements CommandLineRunner {

    /*@Bean
    Logger.Level feignLoggerLevel(){
        return Level.FULL;
    }*/

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SSOApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SSOApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}