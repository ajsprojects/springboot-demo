package com.example.springtest;

import com.example.springtest.database.Repository;
import com.example.springtest.model.User;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Boot demo API",
		description = "Demo API using in memory H2 database",
		version = "2.0"))
public class SpringTestApplication implements CommandLineRunner {

	@Autowired
	Repository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringTestApplication.class, args);
	}
	//http://localhost:8080/swagger-ui.html

	@Override
	public void run(String...args) {
		repository.save(new User(22 , "test", "test@test.com", "le130e"));
		repository.save(new User(26 , "test2", "test2@test2.com", "ng18jr3"));
		repository.save(new User(18 , "test3", "tes3t@test3.com", "pe100fe"));
	}
}
