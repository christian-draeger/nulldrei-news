package de.nulldrei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import de.nulldrei.frontend.startpage.StartPageKeys;

@SpringBootApplication
@EnableConfigurationProperties(StartPageKeys.class)
public class NulldreiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NulldreiApplication.class, args);
	}
}
