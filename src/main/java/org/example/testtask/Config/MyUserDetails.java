package org.example.testtask.Config;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.testtask.Model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDetails implements UserDetails, Serializable  {

    private long user_id;
    private String gmail;
    private String password;

    public static MyUserDetails build(Users user) {

        return new MyUserDetails(
                user.getUser_id(),
                user.getGmail(),
                user.getPassword());
    }

    public static MyUserDetails fromClaims(Claims claims) {
        MyUserDetails userDetails = new MyUserDetails();

        // user_id
        Object idObject = claims.get("userId");
        if (idObject != null) {
            if (idObject instanceof Number) {
                userDetails.setUser_id(((Number) idObject).longValue());
            } else {
                try {
                    userDetails.setUser_id(Long.parseLong(idObject.toString()));
                } catch (NumberFormatException e) {
                }
            }
        }

        // gmail / email
        Object gmailObj = claims.get("gmail");
        if (gmailObj == null) {
            gmailObj = claims.get("email");
        }
        if (gmailObj != null) {
            userDetails.setGmail(gmailObj.toString());
        }

        userDetails.setPassword(null);

        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return gmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

