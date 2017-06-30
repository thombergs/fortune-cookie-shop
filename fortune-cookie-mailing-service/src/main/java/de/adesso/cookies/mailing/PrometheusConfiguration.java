package de.adesso.cookies.mailing;

import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.SpringBootMetricsCollector;
import java.util.Collection;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnablePrometheusEndpoint
public class PrometheusConfiguration {

  private MetricRegistry dropwizardMetricRegistry;

  @Autowired
  public PrometheusConfiguration(MetricRegistry dropwizardMetricRegistry) {
    this.dropwizardMetricRegistry = dropwizardMetricRegistry;
  }

  @PostConstruct
  public void registerPrometheusCollectors() {
    CollectorRegistry.defaultRegistry.clear();
    new StandardExports().register();
    new MemoryPoolsExports().register();
  }

  @Bean
  public SpringBootMetricsCollector springBootMetricsCollector(Collection<PublicMetrics> metrics) {
    SpringBootMetricsCollector springBootMetricsCollector = new SpringBootMetricsCollector(metrics);
    springBootMetricsCollector.register();
    return springBootMetricsCollector;
  }
}