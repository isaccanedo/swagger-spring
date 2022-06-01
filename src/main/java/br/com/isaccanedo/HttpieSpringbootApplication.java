package br.com.isaccanedo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpieSpringbootApplication {

	@SuppressWarnings("unused")
	@Autowired
	private TutorialRepository tutorialRepository;

	public static void main(String[] args) {
		SpringApplication.run(HttpieSpringbootApplication.class, args);
	}

	
}
