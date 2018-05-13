package potatoes.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import potatoes.project.storage.StorageProperties;
import potatoes.project.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableAsync
public class Application {
	
	public static void main(String[] args) { SpringApplication.run(Application.class, args); }

	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> { storageService.init(); };
    }
}
