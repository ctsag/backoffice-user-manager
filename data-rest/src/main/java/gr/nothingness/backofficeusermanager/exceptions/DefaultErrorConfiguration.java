package gr.nothingness.backofficeusermanager.exceptions;

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

    RFC7807Error apiError = new RFC7807Error(
        HttpStatus.valueOf((Integer)originalAttributes.get("status")),
        originalAttributes.get("error").toString(),
        originalAttributes.get("message").toString(),
        originalAttributes.get("path").toString()
    );

    return apiError.toMap();
  }

}
