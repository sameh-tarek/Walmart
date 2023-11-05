package com.ecommerce.walmart;

import com.ecommerce.walmart.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class WalmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalmartApplication.class, args);
	}

}
