package az.task.parceldelivery.model.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class UserrDetails implements UserDetails {

    private final String id;
    private final String fullName;
    private final String encryptedPassword;

    private final String userName;
    private final String[] roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(roles)
                .map(s -> "ROLE_" + s)
                .map(s -> (GrantedAuthority) () -> s)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return encryptedPassword;
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
