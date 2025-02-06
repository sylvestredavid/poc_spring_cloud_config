package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@ConfigurationPropertiesScan
@SpringBootApplication
public class ClientApplication {

	@Autowired
	UserProperties userProperties;

	@Autowired
	AdminProperties adminProperties;

	@Autowired
	BackProperties backProperties;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@CrossOrigin("*")
	@GetMapping(
			value = "/whoami",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public String whoami() {
		return "name : " +
				userProperties.getUsername() +
				"\n" +
				"role : " +
				userProperties.getRole() +
				"\n" +
				"urls : " +
				"\n" +
				"admin : " +
				adminProperties.getUri() +
				" active : " +
				adminProperties.isEnabled() +
				"\n" +
				"back : " +
				backProperties.getUri() +
				" active : " +
				backProperties.isEnabled() +
				"\n";
	}

}
