package gr.nothingness.backofficeusermanager.security.configuration;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.nothingness.backofficeusermanager.configuration.ApplicationConfiguration;
import gr.nothingness.backofficeusermanager.errors.RFC7807Error;
import gr.nothingness.backofficeusermanager.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private ApplicationConfiguration configuration;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationService);
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    if (configuration.isAuthDisabled()) {
      httpSecurity
          .anonymous();
    } else {
      httpSecurity
          .httpBasic()
          .and()
          .authorizeRequests()
          .mvcMatchers(HttpMethod.GET, "/**").hasAuthority(configuration.getReadPermission())
          .anyRequest().hasAuthority(configuration.getWritePermission())
          .and()
          .exceptionHandling()
          .authenticationEntryPoint((request, response, exception) -> {
                RFC7807Error apiError = RFC7807Error
                    .withStatus(UNAUTHORIZED)
                    .andType(configuration.getHttpStatusRefUrl() + "/" + UNAUTHORIZED.value())
                    .andTitle("Unauthorized")
                    .andDetail(exception.getMessage())
                    .andInstance(request.getRequestURI())
                    .build();

                response.setContentType(APPLICATION_JSON_VALUE);
                response.setStatus(UNAUTHORIZED.value());

                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(response.getOutputStream(), apiError.toMap());
              }
          );
    }

    if (configuration.isCsrfDisabled()) {
      httpSecurity
          .csrf()
          .disable();
    }

    if (configuration.isSessionStateless()) {
      httpSecurity
          .sessionManagement()
          .sessionCreationPolicy(STATELESS);
    }
  }

}
