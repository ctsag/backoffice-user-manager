package gr.nothingness.backofficeusermanager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class PagingConfiguration implements PageableHandlerMethodArgumentResolverCustomizer {

  @Override
  public void customize(PageableHandlerMethodArgumentResolver resolver) {
    resolver.setOneIndexedParameters(true);
  }

}
