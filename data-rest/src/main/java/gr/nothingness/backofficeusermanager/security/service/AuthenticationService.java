package gr.nothingness.backofficeusermanager.security.service;

import gr.nothingness.backofficeusermanager.entities.Permission;
import gr.nothingness.backofficeusermanager.repositories.BackofficeUserRepository;
import gr.nothingness.backofficeusermanager.security.facilities.AuthUserDetails;
import gr.nothingness.backofficeusermanager.security.facilities.MixedDigestPasswordEncoder;
import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
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
    UsernamePasswordAuthenticationToken authenticationToken;

    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    AuthUserDetails user = userRepository
        .findByUsername(username)
        .map(AuthUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException(username));

    MixedDigestPasswordEncoder passwordEncoder = new MixedDigestPasswordEncoder();
    passwordEncoder.setSalt(user.getPasswordSalt());

    if (passwordEncoder.matches(password, user.getPassword())) {
      Collection<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);

      authenticationToken = new UsernamePasswordAuthenticationToken(
        new User(username, password, grantedAuthorities),
        password,
        grantedAuthorities
      );
    } else {
      throw new BadCredentialsException("wrong password");
    }

    return authenticationToken;
  }

  private Collection<GrantedAuthority> getGrantedAuthorities(AuthUserDetails user) {
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    for (Permission permission: user.getPermissions()) {
      grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
    }

    return grantedAuthorities;
  }

}
