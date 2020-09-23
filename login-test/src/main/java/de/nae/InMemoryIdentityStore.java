package de.nae;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore {

    private static final Map<String, String> identityMap;
    public static final Set<String> GROUP_SET = new HashSet<>(Collections.singletonList("ADMIN"));

    static {
        final Map<String, String> identityMapCache = new HashMap<>();
        identityMapCache.put("admin", "admin7724");
        identityMapCache.put("user", "user7724");

        identityMap = new ConcurrentHashMap<>(identityMapCache);
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {

        final UsernamePasswordCredential login = (UsernamePasswordCredential) credential;

        final String caller = login.getCaller();
        final String password = login.getPasswordAsString();

        if(identityMap.containsKey(caller) && identityMap.get(caller).equals(password)) {
            return new CredentialValidationResult(caller, GROUP_SET);
        } else {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }
}
