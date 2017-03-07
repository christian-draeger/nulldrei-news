package de.nulldrei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import de.nulldrei.startpage.StartPageConfig;

@SpringBootApplication
@EnableConfigurationProperties(StartPageConfig.class)
public class NulldreiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NulldreiApplication.class, args);
	}
}
