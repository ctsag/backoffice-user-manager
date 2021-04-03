package gr.nothingness.backofficeusermanager.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests(a -> a
            .mvcMatchers(HttpMethod.GET, "/**").hasAuthority("ReadAccess")
            //.mvcMatchers(HttpMethod.GET, "/**").authenticated()
            .anyRequest().hasAuthority("WriteAccess")
            //.anyRequest().authenticated()
        ).httpBasic();

    httpSecurity.csrf().disable();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
