package jv.dbApp.myPrivateDB;

import jv.dbApp.myPrivateDB.domain.Word;
import jv.dbApp.myPrivateDB.repository.WordJpaRepository;
import jv.dbApp.myPrivateDB.service.WordEnrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Random;

@SpringBootApplication
@RequiredArgsConstructor
public class MyPrivateDbApplication {

	private final WordJpaRepository wordJpaRepository;
	private final WordEnrollService wordEnrollService;

	public static void main(String[] args) {
		SpringApplication.run(MyPrivateDbApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(WordEnrollService wordEnrollService) {
		return args -> {
			 wordEnrollService.enrollmentData();
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowedMethods(ALLOWED_METHOD_NAMES.split(","));
			}
		};
	}
}
