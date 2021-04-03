package gr.nothingness.backofficeusermanager.security.service;

import gr.nothingness.backofficeusermanager.repositories.BackofficeUserRepository;
import gr.nothingness.backofficeusermanager.security.facilities.AuthUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

  private final BackofficeUserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .map(AuthUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }

}
