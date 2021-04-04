package gr.nothingness.backofficeusermanager.security.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.nothingness.backofficeusermanager.exceptions.RFC7807Error;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .httpBasic()
        .and()
        .csrf().disable()
        .authorizeRequests()
            .mvcMatchers(HttpMethod.GET, "/**").hasAuthority("UseAdmin")
            .anyRequest().hasAuthority("AssignRights")
        .and()
        .exceptionHandling()
        .authenticationEntryPoint((request, response, exception) -> {
              RFC7807Error apiError = new RFC7807Error(
                  HttpStatus.UNAUTHORIZED,
                  "Unauthorized",
                  exception.getMessage(),
                  request.getPathInfo()
              );

              response.setContentType(MediaType.APPLICATION_JSON_VALUE);

              ObjectMapper mapper = new ObjectMapper();
              mapper.writeValue(response.getOutputStream(), apiError.toMap());
            }
        );
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
