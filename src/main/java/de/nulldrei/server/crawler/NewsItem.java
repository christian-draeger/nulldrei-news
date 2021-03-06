package de.nulldrei.server.crawler;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Component
public class NewsItem {
    private String title;
    private String conclusion;
    private String pictureUrl;
    private String link;
    private String date;
    private String author;
}