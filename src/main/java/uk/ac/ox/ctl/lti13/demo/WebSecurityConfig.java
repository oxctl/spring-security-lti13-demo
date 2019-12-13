package uk.ac.ox.ctl.lti13.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import uk.ac.ox.ctl.lti13.OAuth2AuthorizationRequestRedirectFilter;
import uk.ac.ox.ctl.lti13.security.oauth2.client.lti.authentication.OidcLaunchFlowAuthenticationProvider;
import uk.ac.ox.ctl.lti13.security.oauth2.client.lti.authentication.TargetLinkUriAuthenticationSuccessHandler;
import uk.ac.ox.ctl.lti13.security.oauth2.client.lti.web.OAuth2LoginAuthenticationFilter;
import uk.ac.ox.ctl.lti13.security.oauth2.client.lti.web.OIDCInitiatingLoginRequestResolver;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/resources/**", "/favicon.ico", "/config.json", "/.well-known/jwks.json")
                .permitAll()
                ;

        http.csrf().ignoringAntMatchers("/oauth2/login_initiation/**", "/oauth2/login");

        OidcLaunchFlowAuthenticationProvider oidcLaunchFlowAuthenticationProvider = new OidcLaunchFlowAuthenticationProvider();
        http.authenticationProvider(oidcLaunchFlowAuthenticationProvider);
//        oidcLaunchFlowAuthenticationProvider.setAuthoritiesMapper(new LtiAuthoritiesMapper());
        // This handles step 1 of the IMS SEC
        OIDCInitiatingLoginRequestResolver resolver = new OIDCInitiatingLoginRequestResolver(clientRegistrationRepository, "/oauth2/login_initiation");
        OAuth2AuthorizationRequestRedirectFilter authorizationRequestRedirectFilter = new OAuth2AuthorizationRequestRedirectFilter(resolver);
        http.addFilterAfter(authorizationRequestRedirectFilter, LogoutFilter.class);

        // This handles the actual login
        OAuth2LoginAuthenticationFilter loginFilter = new OAuth2LoginAuthenticationFilter(clientRegistrationRepository, "/oauth2/login");
        // This is to redirect things back the frontend
        TargetLinkUriAuthenticationSuccessHandler successHandler = new TargetLinkUriAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("target_link_uri");
        loginFilter.setAuthenticationSuccessHandler(successHandler);
        ProviderManager authenticationManager = new ProviderManager(Collections.singletonList(oidcLaunchFlowAuthenticationProvider));
        authenticationManager.setAuthenticationEventPublisher(new DefaultAuthenticationEventPublisher(applicationEventPublisher));
        loginFilter.setAuthenticationManager(authenticationManager);
        http.addFilterAfter(loginFilter, AbstractPreAuthenticatedProcessingFilter.class);

        http.headers().frameOptions().disable();
    }

}
