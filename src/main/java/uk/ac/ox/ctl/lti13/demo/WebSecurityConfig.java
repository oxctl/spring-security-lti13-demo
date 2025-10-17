package uk.ac.ox.ctl.lti13.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import uk.ac.ox.ctl.lti13.Lti13Configurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequestsCustomizer -> {
            authorizeHttpRequestsCustomizer.requestMatchers(
                    "/", "/index.html", "/resources/**", "/favicon.ico",
                    "/config.json", "/.well-known/jwks.json", "/error").permitAll();
            authorizeHttpRequestsCustomizer.anyRequest().authenticated();
        });
        http.with(new Lti13Configurer(), lti13Configurer -> lti13Configurer
                .setSecurityContextRepository(new HttpSessionSecurityContextRepository())
        );
        return http.build();
    }

}
