package com.example.RestApi.Configs;

import com.example.RestApi.Entities.Login;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Max;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private Long userId;
    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> roles;

    public static UserPrincipal create(Login user) {
        List<GrantedAuthority> roles = user.getUserRoles().stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleDefinition().getRoleName()))
                .collect(Collectors.toList());
        return new UserPrincipal(user.getUserId(), user.getUserName(), user.getPassword(),
                user.getActive().equals(new Integer(1)) ? true : false, roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
