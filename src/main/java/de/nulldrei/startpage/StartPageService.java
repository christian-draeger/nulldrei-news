package de.nulldrei.startpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartPageService {

    @Autowired
    private StartPageConfig startPageConfig;

    public StartPageModel create() {
        return StartPageModel.builder()
                .title(startPageConfig.getTitle())
                .headline(startPageConfig.getHeadline())
                .build();
    }
}
