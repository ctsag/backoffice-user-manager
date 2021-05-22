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
  private boolean authDisabled = false;
  private String readPermission = "ReadAccess";
  private String writePermission = "WriteAccess";

}
