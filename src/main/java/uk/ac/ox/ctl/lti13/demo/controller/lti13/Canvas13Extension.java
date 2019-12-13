package uk.ac.ox.ctl.lti13.demo.controller.lti13;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Canvas13Extension {

    // Constant for the instructure platform
    public static final String INSTRUCTURE = "canvas.instructure.com";

    private String domain;

    private String toolId;

    private String platform;

    private Lti13Config.PrivacyLevel privacyLevel;

    private Canvas13Settings settings;

    public Canvas13Extension(String domain, String toolId, String platform, Lti13Config.PrivacyLevel privacyLevel, Canvas13Settings settings) {
        this.domain = domain;
        this.toolId = toolId;
        this.platform = platform;
        this.privacyLevel = privacyLevel;
        this.settings = settings;
    }

    public String getDomain() {
        return domain;
    }

    public String getToolId() {
        return toolId;
    }

    public String getPlatform() {
        return platform;
    }

    public Lti13Config.PrivacyLevel getPrivacyLevel() {
        return privacyLevel;
    }

    public Canvas13Settings getSettings() {
        return settings;
    }

}
