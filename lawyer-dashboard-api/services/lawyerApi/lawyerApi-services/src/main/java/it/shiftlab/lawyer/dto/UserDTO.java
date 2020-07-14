package it.shiftlab.lawyer.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

    private String email;

    private String password;

    public UserDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
