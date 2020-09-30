package com.nokia.esp.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * The Nokia ZUUL Server
 *
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulServerApp {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServerApp.class, args);
	}
	
	@LoadBalanced //adding this line solved the issue
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
