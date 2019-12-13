package uk.ac.ox.ctl.lti13.demo.controller.lti13;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Lti13Config {

    public enum PrivacyLevel {
        @JsonProperty("anonymous")ANONYMOUS, @JsonProperty("public") PUBLIC};

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotNull
    private PrivacyLevel privacyLevel;

    @NotEmpty
    private String oidcInitiationUrl;

    @NotEmpty
    private String targetLinkUri;

    private Collection<String> scopes;

    private Collection<Canvas13Extension> extensions;

    private Object publicJwk;

    private String publicJwkUrl;

    private Map<String, String> customFields;

    public Lti13Config(@NotEmpty String title, @NotEmpty String description, @NotNull PrivacyLevel privacyLevel, @NotEmpty String oidcInitiationUrl, @NotEmpty String targetLinkUri, Collection<String> scopes, Collection<Canvas13Extension> extensions, Object publicJwk, String publicJwkUrl, Map<String, String> customFields) {
        this.title = title;
        this.description = description;
        this.privacyLevel = privacyLevel;
        this.oidcInitiationUrl = oidcInitiationUrl;
        this.targetLinkUri = targetLinkUri;
        this.scopes = scopes;
        this.extensions = extensions;
        this.publicJwk = publicJwk;
        this.publicJwkUrl = publicJwkUrl;
        this.customFields = customFields;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public PrivacyLevel getPrivacyLevel() {
        return privacyLevel;
    }

    public String getOidcInitiationUrl() {
        return oidcInitiationUrl;
    }

    public String getTargetLinkUri() {
        return targetLinkUri;
    }

    public Collection<String> getScopes() {
        return scopes;
    }

    public Collection<Canvas13Extension> getExtensions() {
        return extensions;
    }

    public Object getPublicJwk() {
        return publicJwk;
    }

    public String getPublicJwkUrl() {
        return publicJwkUrl;
    }

    public Map<String, String> getCustomFields() {
        return customFields;
    }
}
