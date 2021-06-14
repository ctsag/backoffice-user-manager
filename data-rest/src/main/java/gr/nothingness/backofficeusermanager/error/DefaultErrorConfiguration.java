package gr.nothingness.backofficeusermanager.error;

import gr.nothingness.backofficeusermanager.configuration.ApplicationProperties;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

@Configuration
@Slf4j
public class DefaultErrorConfiguration extends DefaultErrorAttributes {

  @Autowired
  private ApplicationProperties configuration;

  @Override
  public Map<String, Object> getErrorAttributes(WebRequest request, ErrorAttributeOptions options) {
    Map<String, Object> originalAttributes = super.getErrorAttributes(request, options);

    RFC7807Error apiError = RFC7807Error
        .withStatus(HttpStatus.valueOf((Integer)originalAttributes.get("status")))
        .andType(configuration.getHttpStatusRefUrl() + "/" + originalAttributes.get("status"))
        .andTitle(originalAttributes.get("error").toString())
        .andDetail(originalAttributes.get("message").toString())
        .andInstance(originalAttributes.get("path").toString())
        .build();

    log.info(
        "Unhandled error was picked up by the default error configuration: {}",
        originalAttributes.get("error").toString()
    );

    return apiError.toMap();
  }

}
