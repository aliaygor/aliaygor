package com.mygradleproject.publictransportation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = "com.mygradleproject.publictransportation.repository.*")
//@SpringBootApplication(scanBasePackages= {"com.mygradleproject.publictransportation"})
//@EntityScan("com.mygradleproject.publictransportation.entity.*")
//@EnableJpaRepositories(basePackages = {"com.mygradleproject.publictransportation.repository.*"})
//@ComponentScan({"com.mygradleproject.publictransportation"})
//@EntityScan("com.mygradleproject.publictransportation")
//@EnableJpaRepositories("com.mygradleproject.publictransportation")
//@Configuration
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("HELLO WORLD");
		SpringApplication.run(DemoApplication.class, args);
	}

}
