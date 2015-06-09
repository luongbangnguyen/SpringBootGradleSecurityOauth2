package com.asiatech.spring.config;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.asiatech.spring.entity")
@ComponentScan(basePackages = "com.asiatech.spring")
@EnableJpaRepositories(basePackages = "com.asiatech.spring")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public DozerBeanMapperFactoryBean mappingFiles() {
        DozerBeanMapperFactoryBean dozerBean = new DozerBeanMapperFactoryBean();
        return dozerBean;
    }
}
