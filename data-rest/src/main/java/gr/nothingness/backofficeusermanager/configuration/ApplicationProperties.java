package gr.nothingness.backofficeusermanager.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
@Getter @Setter
public class ApplicationProperties {

  private String basePath = "/";
  private String httpStatusRefUrl = "https://httpstatuses.com";
  private String readPermission = "ReadAccess";
  private String writePermission = "WriteAccess";
  private boolean authDisabled = false;
  private boolean csrfDisabled = true;
  private boolean sessionStateless = true;

}
