package com.pmsauth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmsauth.constants.Constants;
import com.pmsauth.model.Error;
import com.pmsauth.model.User;
import com.pmsauth.model.UserLogin;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakAdminClientService {

    private final Keycloak keycloakConfig;

    private final RestTemplate restTemplate;

    @Value("${realm}")
    private String realm;

    @Value("${client}")
    private String client;

    @Value("${key.cloak.clientSecret}")
    private String clientSecret;

    @Value("${tokenUrl}")
    private String url;

    @Value("${grant.type}")
    private String grantType;

    public KeycloakAdminClientService(Keycloak keycloakConfig, RestTemplate restTemplate) {
        this.keycloakConfig = keycloakConfig;
        this.restTemplate = restTemplate;
    }

    public RealmResource getRealm() {
        return keycloakConfig.realm(realm);
    }

    /**
     * add user to the key cloak
     *
     * @param user
     */
    public void addUser(User user) {
        UsersResource usersResource = getRealm().users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getEmail());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        usersResource.create(kcUser);

    }

    /**
     * add roles to the realm
     *
     * @param new_role_name
     */
    public void addRealmRole(String new_role_name) {
        if (!getAllRoles().contains(new_role_name)) {
            RoleRepresentation roleRep = new RoleRepresentation();
            roleRep.setName(new_role_name);
            getRealm().roles().create(roleRep);
        }
    }

    /**
     * add role to specific client
     *
     * @param new_role_name
     */
    public void addClientRole(String new_role_name) {
        if (!getAllRoles().contains(new_role_name)) {
            RoleRepresentation roleRep = new RoleRepresentation();
            roleRep.setName(new_role_name);
            ClientRepresentation clientRep =
                    getRealm()
                            .clients()
                            .findByClientId(client)
                            .get(0);

            getRealm()
                    .clients()
                    .get(clientRep.getId())
                    .roles()
                    .create(roleRep);
        }
    }

    /**
     * add realm role to the user
     *
     * @param userName
     * @param role_name
     */
    public void addRealmRoleToUser(String userName, String role_name) {
        String userId = getRealm()
                .users()
                .search(userName)
                .get(0)
                .getId();
        UserResource user =
                getRealm()
                        .users()
                        .get(userId);

        List<RoleRepresentation> roleToAdd = new LinkedList<>();
        roleToAdd.add(getRealm().roles()
                .get(role_name)
                .toRepresentation());
        user.roles().realmLevel().add(roleToAdd);
    }

    public String getUserRole(String userName) {
        String userId = getRealm()
                .users()
                .search(userName)
                .get(0)
                .getId();
        UserResource user =
                getRealm()
                        .users()
                        .get(userId);

        return "";
    }

    /**
     * give all register users
     *
     * @return
     */
    public List<String> getAllUser() {
        return getRealm()
                .users()
                .list()
                .stream()
                .map(UserRepresentation::getUsername)
                .collect(Collectors.toList());
    }

    /**
     * give all realm roles
     *
     * @return
     */
    public List<String> getAllRoles() {
        return getRealm()
                .roles()
                .list()
                .stream()
                .map(RoleRepresentation::getName)
                .collect(Collectors.toList());
    }

    /**
     * set password instance in key cloak
     *
     * @param password
     * @return
     */
    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    /**
     *
     * @param userLogin
     * @return
     */
    public Object validateUser(UserLogin userLogin) {
        Object response = null;
        try {
            MultiValueMap<String, String> map = setHttpParameters(userLogin);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
            response =
                    restTemplate.exchange(url,
                            HttpMethod.POST,
                            entity,
                            Object.class).getBody();
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                String responseString = "User ID/Password not matched !";
                response = new Error(responseString, "Please check username and password");
            }
        }
        return response;
    }

    public boolean resetPassword(String emailId, String newPassword){
        boolean isPasswordUpdated = false;
        try {
            UserRepresentation userRepresentation = getRealm().users().search(emailId).get(0);
            if(userRepresentation!=null){
                CredentialRepresentation cred = new CredentialRepresentation();
                cred.setType(CredentialRepresentation.PASSWORD);
                cred.setValue(newPassword);
                cred.setTemporary(false);

                getRealm().users().get(userRepresentation.getId()).resetPassword(cred);
                isPasswordUpdated = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isPasswordUpdated;
    }

    private MultiValueMap<String, String> setHttpParameters(UserLogin userLogin) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(Constants.CLIENT_SECRET, clientSecret);
        map.add(Constants.USERNAME, userLogin.getUsername());
        map.add(Constants.PASSWORD, userLogin.getPassword());
        map.add(Constants.GRANT_TYPE, grantType);
        map.add(Constants.CLIENT_ID, client);
        return map;
    }

}
