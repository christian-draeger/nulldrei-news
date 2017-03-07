package de.nulldrei.startpage;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "startpage.keys")
public class StartPageConfig {

    private String title;
    private String headline;
}
