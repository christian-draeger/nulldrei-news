package de.nulldrei.frontend.startpage;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "startpage.keys")
public class StartPageKeys {

    private String title;
    private String headline;
}
