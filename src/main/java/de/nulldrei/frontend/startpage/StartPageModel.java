package de.nulldrei.frontend.startpage;

import java.util.List;

import de.nulldrei.server.pnn.PnnElement;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StartPageModel {
    String headline;
    String title;
    List<PnnElement> pnnElements;
}
