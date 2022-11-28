package com.pmsauth.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KeycloakConfig {
    static Keycloak keycloak = null;

    @Value("${key.cloak.server}")
    public String serverUrl;

    @Value("${key.cloak.master.realm}")
    private String masterRealm;

    @Value("${key.cloak.clientId}")
    private String clientId;

    @Value("${key.cloak.clientSecret}")
    private String clientSecret;

    @Value("${key.cloak.userName}")
    private String userName;

    @Value("${key.cloak.password}")
    private String password;

    @Bean
    public Keycloak getInstance() {
        if (keycloak == null) {

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(masterRealm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilder()
                            .connectionPoolSize(10)
                            .build())
                    .build();
        }
        return keycloak;
    }
}
