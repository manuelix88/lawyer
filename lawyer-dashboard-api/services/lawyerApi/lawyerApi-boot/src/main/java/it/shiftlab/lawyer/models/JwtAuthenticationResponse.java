package it.shiftlab.lawyer.models;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String username;
    Collection<? extends GrantedAuthority> authorities;
    private final String token;

    public JwtAuthenticationResponse(String username, Collection<? extends GrantedAuthority> authorities, String token) {
        this.username = username;
        this.authorities = authorities;
        this.token = token;
    }

    public String getUsername() {
        return this.username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }
}
