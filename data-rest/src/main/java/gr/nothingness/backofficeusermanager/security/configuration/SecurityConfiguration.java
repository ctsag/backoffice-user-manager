package gr.nothingness.backofficeusermanager.security.configuration;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.nothingness.backofficeusermanager.errors.RFC7807Error;
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
        .httpBasic()
        .and()
        .csrf().disable()
        .authorizeRequests()
            .mvcMatchers(HttpMethod.GET, "/**").hasAuthority("UseAdmin")
            .anyRequest().hasAuthority("AssignRights")
        .and()
        .exceptionHandling()
        .authenticationEntryPoint((request, response, exception) -> {
              RFC7807Error apiError = RFC7807Error
                  .withStatus(UNAUTHORIZED)
                  .andType("https://httpstatuses.com/401")
                  .andTitle("Unauthorized")
                  .andDetail(exception.getMessage())
                  .andInstance(request.getRequestURI())
                  .build();

              response.setContentType(APPLICATION_JSON_VALUE);

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
