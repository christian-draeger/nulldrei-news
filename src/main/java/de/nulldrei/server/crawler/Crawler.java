package de.nulldrei.server.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Crawler {

    @Value("${config.useragent}")
    String userAgent;

    @Value("${config.referrer}")
    String referrer;

    public Document getPage(String url) {
        try {
            return Jsoup.connect(url)
                    .userAgent(userAgent)
                    .referrer(referrer)
                    .get();
        } catch (IOException e) {
            log.warn("Error querying " + url, e);
        }
        return null;
    }
}
