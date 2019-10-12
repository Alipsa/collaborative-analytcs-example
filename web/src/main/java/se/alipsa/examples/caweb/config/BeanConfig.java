package se.alipsa.examples.caweb.config;

import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

public class BeanConfig {

  @Bean
  public GracefulShutdown gracefulShutdown() {
      return new GracefulShutdown();
  }
  
  @Bean
  public ConfigurableServletWebServerFactory webServerFactory(final GracefulShutdown gracefulShutdown) {
      TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
      factory.addConnectorCustomizers(gracefulShutdown);
      return factory;
  }
}