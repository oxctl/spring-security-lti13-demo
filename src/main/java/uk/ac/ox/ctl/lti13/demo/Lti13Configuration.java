package uk.ac.ox.ctl.lti13.demo;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import uk.ac.ox.ctl.lti13.KeyPairService;
import uk.ac.ox.ctl.lti13.SingleKeyPairService;
import uk.ac.ox.ctl.lti13.TokenRetriever;
import uk.ac.ox.ctl.lti13.nrps.NamesRoleService;
import uk.ac.ox.ctl.lti13.utils.KeyStoreKeyFactory;

import java.net.MalformedURLException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class Lti13Configuration {

    private final Logger log = LoggerFactory.getLogger(Lti13Configuration.class);

    /**
     * The location of the JWK key file.
     */
    @Value("${lti.jwk.location:config/jwk.jks}")
    private String location;

    /**
     * The password for the JWK key file.
     */
    @Value("${lti.jwk.password:store-pass}")
    private String storePassword;

    /***
     * The ID of the key in the JKS file.
     */
    @Value("${lti.jwk.id:lti-jwt-id}")
    private String jwtId;

    @Bean
    public NamesRoleService namesRoleService(ClientRegistrationRepository clientRegistrationRepository, TokenRetriever tokenRetriever) {
        return new NamesRoleService(clientRegistrationRepository, tokenRetriever);
    }

    @Bean
    public TokenRetriever tokenRetriever(KeyPairService keyPairService) {
        return new TokenRetriever(keyPairService);
    }

    @Bean
    public KeyPairService keyPairService(KeyPair keyPair) {
        return new SingleKeyPairService(keyPair, jwtId);
    }

    @Bean
    public KeyPair keyPair() {
        Resource resource = null;
        try {
            resource = new FileUrlResource(location);
            if (resource.exists()) {
                KeyStoreKeyFactory ksFactory = new KeyStoreKeyFactory(resource, storePassword.toCharArray());
                log.info("Loaded key from "+ location);
                return ksFactory.getKeyPair("jwt");
            } else {
                log.info("Generated a keypair, this shouldn't be used in production.");
                return KeyPairGenerator.getInstance("RSA").generateKeyPair();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to load jwt");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate keypair");
        }
    }

    @Bean
    public JWKSet jwkSet() {
        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic())
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID(jwtId);
        return new JWKSet(builder.build());
    }
}
