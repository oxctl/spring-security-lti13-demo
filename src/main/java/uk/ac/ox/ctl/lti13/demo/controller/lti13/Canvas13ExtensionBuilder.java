package uk.ac.ox.ctl.lti13.demo.controller.lti13;

public class Canvas13ExtensionBuilder {
    private String domain;
    private String toolId;
    private String platform;
    private Lti13Config.PrivacyLevel privacyLevel;
    private Canvas13Settings settings;

    public Canvas13ExtensionBuilder domain(String domain) {
        this.domain = domain;
        return this;
    }

    public Canvas13ExtensionBuilder toolId(String toolId) {
        this.toolId = toolId;
        return this;
    }

    public Canvas13ExtensionBuilder platform(String platform) {
        this.platform = platform;
        return this;
    }

    public Canvas13ExtensionBuilder privacyLevel(Lti13Config.PrivacyLevel privacyLevel) {
        this.privacyLevel = privacyLevel;
        return this;
    }

    public Canvas13ExtensionBuilder settings(Canvas13Settings settings) {
        this.settings = settings;
        return this;
    }

    public Canvas13Extension createCanvas13Extension() {
        return new Canvas13Extension(domain, toolId, platform, privacyLevel, settings);
    }
}