package uk.ac.ox.ctl.lti13.demo.controller.lti13;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Canvas13Settings {

    private String text;
    private String iconUrl;
    private String selectionHeight;
    private String selectionWidth;
    private Lti13Config.PrivacyLevel privacyLevel;
    private Collection<Canvas13Placement> placements;

    public Canvas13Settings(String text, String iconUrl, String selectionHeight, String selectionWidth, Lti13Config.PrivacyLevel privacyLevel, Collection<Canvas13Placement> placements) {
        this.text = text;
        this.iconUrl = iconUrl;
        this.selectionHeight = selectionHeight;
        this.selectionWidth = selectionWidth;
        this.privacyLevel = privacyLevel;
        this.placements = placements;
    }

    public String getText() {
        return text;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getSelectionHeight() {
        return selectionHeight;
    }

    public String getSelectionWidth() {
        return selectionWidth;
    }

    public Lti13Config.PrivacyLevel getPrivacyLevel() {
        return privacyLevel;
    }

    public Collection<Canvas13Placement> getPlacements() {
        return placements;
    }
}
