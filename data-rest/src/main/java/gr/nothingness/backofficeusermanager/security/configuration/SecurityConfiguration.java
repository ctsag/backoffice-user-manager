package gr.nothingness.backofficeusermanager.security.configuration;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.nothingness.backofficeusermanager.errors.RFC7807Error;
import gr.nothingness.backofficeusermanager.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private SecurityProperties properties;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationService);
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    if (properties.isAuthDisabled()) {
      httpSecurity
          .anonymous();
    } else {
      httpSecurity
          .httpBasic()
          .and()
          .csrf()
              .disable()
          .authorizeRequests()
              .mvcMatchers(GET, "/actuator/health").permitAll()
              .mvcMatchers(GET, properties.getBasePath()).permitAll()
              .mvcMatchers(GET, "/**").hasAuthority(properties.getReadPermission())
              .anyRequest().hasAuthority(properties.getWritePermission())
          .and()
          .sessionManagement()
              .sessionCreationPolicy(STATELESS)
          .and()
          .exceptionHandling()
              .authenticationEntryPoint((request, response, exception) -> {
                    RFC7807Error apiError = RFC7807Error
                        .withStatus(UNAUTHORIZED)
                        .andType(properties.getHttpStatusRefUrl() + "/" + UNAUTHORIZED.value())
                        .andTitle("Unauthorized")
                        .andDetail(exception.getMessage())
                        .andInstance(request.getRequestURI())
                        .build();

                    response.setContentType(APPLICATION_JSON_VALUE);
                    response.setStatus(UNAUTHORIZED.value());

                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writeValue(response.getOutputStream(), apiError.toMap());
              });
    }
  }

}
