package gr.nothingness.backofficeusermanager.errors;

import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

@Configuration
public class DefaultErrorConfiguration extends DefaultErrorAttributes {

  @Override
  public Map<String, Object> getErrorAttributes(WebRequest request, ErrorAttributeOptions options) {
    Map<String, Object> originalAttributes = super.getErrorAttributes(request, options);

    RFC7807Error apiError = RFC7807Error
        .withStatus(HttpStatus.valueOf((Integer)originalAttributes.get("status")))
        .andType("https://httpstatuses.com/" + originalAttributes.get("status"))
        .andTitle(originalAttributes.get("error").toString())
        .andDetail(originalAttributes.get("message").toString())
        .andInstance(originalAttributes.get("path").toString())
        .build();

    return apiError.toMap();
  }

}
