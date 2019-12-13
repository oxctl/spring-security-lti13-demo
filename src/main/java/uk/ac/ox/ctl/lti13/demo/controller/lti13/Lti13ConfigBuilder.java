package uk.ac.ox.ctl.lti13.demo.controller.lti13;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Map;

public class Lti13ConfigBuilder {
    private @NotEmpty String title;
    private @NotEmpty String description;
    private Lti13Config.@NotNull PrivacyLevel privacyLevel;
    private @NotEmpty String oidcInitiaionUrl;
    private @NotEmpty String targetLinkUri;
    private Collection<String> scopes;
    private Collection<Canvas13Extension> extensions;
    private Object publicJwk;
    private String publicJwkUrl;
    private Map<String, String> customFields;

    public Lti13ConfigBuilder title(@NotEmpty String title) {
        this.title = title;
        return this;
    }

    public Lti13ConfigBuilder description(@NotEmpty String description) {
        this.description = description;
        return this;
    }

    /**
     * It appears that this doesn't control anything, although it is returned in responses we get back.
     * @see Canvas13ExtensionBuilder#privacyLevel(Lti13Config.PrivacyLevel)
     */
    public Lti13ConfigBuilder privacyLevel(Lti13Config.@NotNull PrivacyLevel privacyLevel) {
        this.privacyLevel = privacyLevel;
        return this;
    }

    public Lti13ConfigBuilder oidcInitiaionUrl(@NotEmpty String oidcInitiaionUrl) {
        this.oidcInitiaionUrl = oidcInitiaionUrl;
        return this;
    }

    public Lti13ConfigBuilder targetLinkUri(@NotEmpty String targetLinkUri) {
        this.targetLinkUri = targetLinkUri;
        return this;
    }

    public Lti13ConfigBuilder scopes(Collection<String> scopes) {
        this.scopes = scopes;
        return this;
    }

    public Lti13ConfigBuilder extensions(Collection<Canvas13Extension> extensions) {
        this.extensions = extensions;
        return this;
    }

    public Lti13ConfigBuilder publicJwk(Object publicJwk) {
        this.publicJwk = publicJwk;
        return this;
    }

    public Lti13ConfigBuilder publicJwkUrl(String publicJwkUrl) {
        this.publicJwkUrl = publicJwkUrl;
        return this;
    }

    public Lti13ConfigBuilder customFields(Map<String, String> customFields) {
        this.customFields = customFields;
        return this;
    }

    public Lti13Config createLti13Config() {
        return new Lti13Config(title, description, privacyLevel, oidcInitiaionUrl, targetLinkUri, scopes, extensions, publicJwk, publicJwkUrl, customFields);
    }
}