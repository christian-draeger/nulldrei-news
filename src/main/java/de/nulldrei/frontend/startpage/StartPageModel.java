package de.nulldrei.frontend.startpage;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StartPageModel {
    String headline;
    String title;
}
