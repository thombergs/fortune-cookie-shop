package de.adesso.cookies.mailing;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
class PrometheusConfiguration {

  @PostConstruct
  void registerPrometheusCollectors() {
    CollectorRegistry.defaultRegistry.clear();
    new StandardExports().register();
    new MemoryPoolsExports().register();
  }

  @Bean
  ServletRegistrationBean registerPrometheusServlet() {
    return new ServletRegistrationBean(new MetricsServlet(), "/prometheus");
  }
}