package jv.dbApp.myPrivateDB;

import jv.dbApp.myPrivateDB.domain.Word;
import jv.dbApp.myPrivateDB.repository.WordJpaRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(MyPrivateDbApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(WordJpaRepository wordJpaRepository) {
		return args -> {
//			wordJpaRepository.save(new Word("철학", "philosophy"));
//			wordJpaRepository.save(new Word("수학", "math"));
//			wordJpaRepository.save(new Word("영어", "english"));
//			wordJpaRepository.save(new Word("물리", "physics"));
//			wordJpaRepository.save(new Word("과학", "science"));
//			wordJpaRepository.save(new Word("음악", "music"));
//			wordJpaRepository.save(new Word("미술", "art"));
//			wordJpaRepository.save(new Word("사회", "society"));

		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080");
			}
		};
	}
}
