package com.example.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Map;
@CrossOrigin("*")
@RestController
@SpringBootApplication
@EnableConfigServer
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@GetMapping(
			value = "/config")
	public Map<String, Object> getConfig() throws IOException {
		File file = new File("C:/Users/Polyconseil/Documents/spring-cloud-config/config-repo/config-client-development.yaml");

		// Initialiser ObjectMapper pour YAML
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

		// Lire le fichier YAML en tant que Map
		return objectMapper.readValue(file, Map.class);
	}

	@PutMapping(
			value = "/config")
	public String updateConfig(@RequestBody Map<String, Object> newYaml) throws IOException {
		File file = new File("C:/Users/Polyconseil/Documents/spring-cloud-config/config-repo/config-client-development.yaml");

		// Initialiser ObjectMapper pour YAML
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

		objectMapper.writeValue(file, newYaml);

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/actuator/refresh";

		// Créer les headers avec Content-Type
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Créer une requête avec un corps vide
		HttpEntity<String> entity = new HttpEntity<>("{}", headers);

		// Envoyer la requête POST
		try{
			restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		} catch (RestClientException e) {
			System.err.println("une erreur est survenue au refresh");
		}

		return "ok";
	}

}
