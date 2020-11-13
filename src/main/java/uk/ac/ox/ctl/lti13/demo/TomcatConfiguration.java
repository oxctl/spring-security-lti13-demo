package uk.ac.ox.ctl.lti13.demo;

import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.ac.ox.ctl.lti13.demo.utils.SameSiteCookeValve;

/**
 * This is needed because often a LTI tool will run in an iframe and with the recent stricter cookie rules
 * browsers are rejecting cookies set in an iframe from the LTI redirect unless they have a SameSite cookie policy
 * of "None".
 */
@Configuration
public class TomcatConfiguration {

    @Bean
    WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
        return tomcatServletWebServerFactory -> {
            tomcatServletWebServerFactory.addContextValves(new SameSiteCookeValve());
            tomcatServletWebServerFactory.addContextCustomizers(context -> {
                Rfc6265CookieProcessor processor = new Rfc6265CookieProcessor();
                processor.setSameSiteCookies("None");
                context.setCookieProcessor(processor);
            });
        };
    }
}
