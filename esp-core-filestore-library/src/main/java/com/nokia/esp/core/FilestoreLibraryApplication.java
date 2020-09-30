package com.nokia.esp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The File Store Application
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FilestoreLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilestoreLibraryApplication.class, args);
	}

}
