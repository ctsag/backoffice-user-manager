package gr.nothingness.backofficeusermanager.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationConfiguration {

  @Getter @Setter private boolean authDisabled = false;
  @Getter @Setter private boolean csrfDisabled = true;
  @Getter @Setter private String readPermission = "ReadAccess";
  @Getter @Setter private String writePermission = "WriteAccess";
  @Getter @Setter private String httpStatusRefUrl = "https://httpstatuses.com";

}
