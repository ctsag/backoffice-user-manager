package gr.nothingness.backofficeusermanager.security.service;

import gr.nothingness.backofficeusermanager.entities.BackofficeGroup;
import gr.nothingness.backofficeusermanager.entities.Permission;
import gr.nothingness.backofficeusermanager.repositories.BackofficeUserRepository;
import gr.nothingness.backofficeusermanager.security.facilities.AuthenticatedUserDetails;
import gr.nothingness.backofficeusermanager.security.facilities.MixedDigestPasswordEncoder;
import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationProvider {

  private final BackofficeUserRepository userRepository;

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    AuthenticatedUserDetails user = userRepository
        .findByUsername(username)
        .map(AuthenticatedUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("Wrong username"));

    performAuthenticationChecks(user, password);

    Collection<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);

    return new UsernamePasswordAuthenticationToken(
        new User(username, password, grantedAuthorities),
        password,
        getGrantedAuthorities(user)
    );
  }

  private void performAuthenticationChecks(AuthenticatedUserDetails user, String password) {
    MixedDigestPasswordEncoder passwordEncoder = new MixedDigestPasswordEncoder();
    passwordEncoder.setSalt(user.getPasswordSalt());

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Wrong password");
    }

    if (!user.isAccountNonExpired()) {
      throw new AccountExpiredException("The account is expired");
    }

    if (!user.isAccountNonLocked()) {
      throw new LockedException("The account is locked");
    }

    if (!user.isCredentialsNonExpired()) {
      throw new CredentialsExpiredException("The password has expired and needs to be changed");
    }

    if (!user.isEnabled()) {
      throw new DisabledException("The account is disabled");
    }
  }

  private Collection<GrantedAuthority> getGrantedAuthorities(AuthenticatedUserDetails user) {
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    for (Permission permission: user.getPermissions()) {
      grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
    }

    for (BackofficeGroup group: user.getGroups()) {
      for (Permission permission: group.getPermissions()) {
        grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
      }
    }

    return grantedAuthorities;
  }

}
