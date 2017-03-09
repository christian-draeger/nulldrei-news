package de.nulldrei.frontend.startpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.nulldrei.server.officialpage.OfficialPage;

@Component
public class StartPageService {

    @Autowired
    private StartPageKeys startPageKeys;

    @Autowired
    private OfficialPage officialPage;

    public StartPageModel create() {
        return StartPageModel.builder()
                .title(startPageKeys.getTitle())
                .headline(startPageKeys.getHeadline())
                .newsItems(officialPage.getItems())
                .build();
    }
}
