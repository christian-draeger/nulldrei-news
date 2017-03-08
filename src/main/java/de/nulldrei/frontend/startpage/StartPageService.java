package de.nulldrei.frontend.startpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartPageService {

    @Autowired
    private StartPageKeys startPageKeys;

    public StartPageModel create() {
        return StartPageModel.builder()
                .title(startPageKeys.getTitle())
                .headline(startPageKeys.getHeadline())
                .build();
    }
}
