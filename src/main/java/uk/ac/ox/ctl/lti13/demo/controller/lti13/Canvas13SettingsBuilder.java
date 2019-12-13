package uk.ac.ox.ctl.lti13.demo.controller.lti13;

import java.util.Collection;

public class Canvas13SettingsBuilder {
    private String text;
    private String iconUrl;
    private String selectionHeight;
    private String selectionWidth;
    private Lti13Config.PrivacyLevel privacyLevel;
    private Collection<Canvas13Placement> placements;

    public Canvas13SettingsBuilder text(String text) {
        this.text = text;
        return this;
    }

    public Canvas13SettingsBuilder iconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public Canvas13SettingsBuilder selectionHeight(String selectionHeight) {
        this.selectionHeight = selectionHeight;
        return this;
    }

    public Canvas13SettingsBuilder selectionWidth(String selectionWidth) {
        this.selectionWidth = selectionWidth;
        return this;
    }

    /**
     * It appears that this doesn't control anything, although it is returned in responses we get back.
     * @see Canvas13ExtensionBuilder#privacyLevel(Lti13Config.PrivacyLevel)
     */
    public Canvas13SettingsBuilder privacyLevel(Lti13Config.PrivacyLevel privacyLevel) {
        this.privacyLevel = privacyLevel;
        return this;
    }

    public Canvas13SettingsBuilder placements(Collection<Canvas13Placement> placements) {
        this.placements = placements;
        return this;
    }

    public Canvas13Settings createCanvas13Settings() {
        return new Canvas13Settings(text, iconUrl, selectionHeight, selectionWidth, privacyLevel, placements);
    }
}