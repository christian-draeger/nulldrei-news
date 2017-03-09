package de.nulldrei.frontend.startpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.nulldrei.server.pnn.Pnn;

@Component
public class StartPageService {

    @Autowired
    private StartPageKeys startPageKeys;

    @Autowired
    private Pnn pnn;

    public StartPageModel create() {
        return StartPageModel.builder()
                .title(startPageKeys.getTitle())
                .headline(startPageKeys.getHeadline())
                .pnnElements(pnn.getPnn())
                .build();
    }
}
