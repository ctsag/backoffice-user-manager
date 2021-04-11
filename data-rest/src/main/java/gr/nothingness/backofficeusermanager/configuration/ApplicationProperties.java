package gr.nothingness.backofficeusermanager.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

  @Getter @Setter private String basePath = "/";
  @Getter @Setter private String httpStatusRefUrl = "https://httpstatuses.com";

}
