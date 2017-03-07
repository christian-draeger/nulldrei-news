package de.nulldrei.startpage;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StartPageModel {
    String headline;
    String title;
}
