package gr.nothingness.backofficeusermanager.security.facilities;

import gr.nothingness.backofficeusermanager.entities.BackofficeUser;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUserDetails extends BackofficeUser implements UserDetails {

  public AuthUserDetails(BackofficeUser user) {
    super(user);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return super.getPermissions()
        .stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public String getUsername() {
    return super.getUsername();
  }

  @Override
  public String getPassword() {
    return super.getPassword();
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
    return getStatus() == Status.A;
  }

}
