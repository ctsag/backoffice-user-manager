package gr.nothingness.backofficeusermanager.configuration;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.nothingness.backofficeusermanager.error.RFC7807Error;
import gr.nothingness.backofficeusermanager.security.service.AuthenticationService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private ApplicationProperties properties;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationService);
  }

  @Override
  public void configure(WebSecurity webSecurity) {
    if (properties.isAuthDisabled()) {
      webSecurity.ignoring().antMatchers("/**");
    }
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .httpBasic()
        .and()
        .authorizeRequests()
            .mvcMatchers(GET, "/actuator/**").permitAll()
            .mvcMatchers(GET, properties.getBasePath()).permitAll()
            .mvcMatchers(GET, "/**").hasAuthority(properties.getReadPermission())
            .anyRequest().hasAuthority(properties.getWritePermission())
        .and()
        .exceptionHandling()
            .authenticationEntryPoint((request, response, exception) ->
                generateError(request, response, exception, UNAUTHORIZED)
            )
            .accessDeniedHandler((request, response, exception) ->
                generateError(request, response, exception, FORBIDDEN)
            );

    if (properties.isSessionStateless()) {
      httpSecurity
          .sessionManagement()
              .sessionCreationPolicy(STATELESS)
          .and()
          .csrf()
              .disable();
    }

    if (properties.isCsrfDisabled()) {
      httpSecurity
          .csrf()
              .disable();
    }
  }

  private void generateError(
      HttpServletRequest request,
      HttpServletResponse response,
      Exception exception,
      HttpStatus status
  ) throws IOException {
    RFC7807Error apiError = RFC7807Error
        .withStatus(status)
        .andType(properties.getHttpStatusRefUrl() + "/" + status.value())
        .andTitle(status.getReasonPhrase())
        .andDetail(exception.getMessage())
        .andInstance(request.getRequestURI())
        .build();

    response.setContentType(APPLICATION_PROBLEM_JSON_VALUE);
    response.setStatus(status.value());

    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getOutputStream(), apiError.toMap());
  }

}
