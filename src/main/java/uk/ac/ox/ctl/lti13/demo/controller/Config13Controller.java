package uk.ac.ox.ctl.lti13.demo.controller;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uk.ac.ox.ctl.lti13.demo.controller.lti13.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static uk.ac.ox.ctl.lti13.demo.controller.lti13.Canvas13Extension.INSTRUCTURE;

@RestController
public class Config13Controller {

    @Value("${spring.application.name:LTI Tool}")
    private String title;

    @Value("${lti.application.description:Tool description.}")
    private String description;

    @Value("${lti.jwk.id:lti-jwt-id}")
    private String jwtId;

    private final JWKSet jwkSet;

    public Config13Controller(JWKSet jwkSet) {
        this.jwkSet = jwkSet;
    }

    @GetMapping("/config.json")
    public Lti13Config getConfig(HttpServletRequest request) {
        String urlPrefix = ServletUriComponentsBuilder.fromContextPath(request).toUriString();
        Canvas13Placement linkSelection = new Canvas13PlacementBuilder()
                .placement(Canvas13Placement.Placement.LINK_SELECTION)
                .enabled(false)
                .messageType(Canvas13Placement.MessageType.LtiDeepLinkingRequest)
                .createCanvas13Placement();
        Canvas13Placement assignmentSelection = new Canvas13PlacementBuilder()
                .placement(Canvas13Placement.Placement.ASSIGNMENT_SELECTION)
                .enabled(true)
                .messageType(Canvas13Placement.MessageType.LtiDeepLinkingRequest)
                .createCanvas13Placement();
        List<Canvas13Placement> placements = Arrays.asList(linkSelection, assignmentSelection);
        Canvas13Settings canvas13Settings = new Canvas13SettingsBuilder()
                .placements(placements)
                .createCanvas13Settings();
        Collection<Canvas13Extension> extensions  = Collections.singleton(new Canvas13ExtensionBuilder()
                .platform(INSTRUCTURE)
                .domain(request.getServerName())
                .privacyLevel(Lti13Config.PrivacyLevel.PUBLIC)
                .settings(canvas13Settings)
                .createCanvas13Extension());
        Map<String, String> customFields = new HashMap<>();
        customFields.put("canvas_css_common", "$Canvas.css.common");
        customFields.put("com_instructure_brand_config_json_url", "$com.instructure.brandConfigJS.url");
        return new Lti13ConfigBuilder()
                .title(title)
                .description(description)
                .oidcInitiaionUrl(urlPrefix + "/oauth2/login_initiation/canvas")
                .targetLinkUri(urlPrefix)
                .extensions(extensions)
                .publicJwk(jwkSet.getKeyByKeyId(jwtId).toPublicJWK().toJSONObject())
                .customFields(customFields)
                .createLti13Config();
    }

}
