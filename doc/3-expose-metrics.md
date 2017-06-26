# Metriken in die Beispielanwendung einbauen

In dieser Übung wird die Beispielanwendung erweitert, um einige Metriken zu erfassen
und diese über eine Schnittstelle zur Verfügung zu stellen. Nimm dir für diese
Aufgaben ca. **60 Minuten** Zeit.

## Metriken erfassen

Für die Erfassung der Metriken nutzen wir Dropwizard Metrics. Füge die folgenden
Metriken (und ggf. weitere, die du für sinnvoll erachtest) in die Server-Anwendungen
ein. Nutze als Grundlage die Code-Beispiele aus den Folien oder die Webseite unter
(http://metrics.dropwizard.io/3.2.2/getting-started.html)[http://metrics.dropwizard.io/3.2.2/getting-started.html].

* Durchsatz von Warenkorb-Checkouts pro Sekunde
* Durchsatz von versendeten Mails pro Sekunde
* Durchsatz von Produkt-Listing-Anfragen
* Antwortzeiten von Warenkorb-Checkouts
* Antwortzeiten des Mailversands
* Antwortzeiten von Produkt-Listing-Anfragen

Beantworte dabei die folgenden Fragen:

* an welchen Stellen ist es am sinnvollsten, die Erfassung der Metriken einzubauen? (Rest-Controller? Service-Schicht?)
* wie könnte man die Fehlerrate eines Service als Metrik erfassen?
* diskutiert die einzelnen Metriken, die DropWizard erzeugt und findet heraus, was sie genau bedeuten
  * fiveMinuteRate
  * 99thPercentile
  * median
  * meanRate
  * mean

Um zu prüfen, ob die Metriken erfasst werden, greife auf den "/metrics"-Endpoint der 
jeweiligen Serveranwendung zu (beachte, dass Metriken erst angezeigt werden, nachdem sie durch eine
Aktion zum ersten mal berechnet wurden):

Metrics-Endpunkte der jeweiligen Serveranwendung:

* fortune-cookie-fulfillment-service: http://localhost:8081/metrics
* fortune-cookie-mailing-service: http://localhost:8082/metrics
* fortune-cookie-product-service: http://localhost:8083/metrics


## Metriken in Prometheus-Format transformieren

Die Metriken liegen bisher nur im JSON-Format vor. Damit sie von Prometheus gescraped werden können,
müssen sie in das Prometheus-Datenformat übertragen werden. Baue hierfür die folgende Klasse in 
jede der drei Service-Anwendungen ein:

```java
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
    new DropwizardExports(dropwizardMetricRegistry).register();
    ... // more metric exports
  }
}
```

Prüfe, ob die Metriken über den Endpoint `/prometheus` im Prometheus-Format angeboten werden.

## Metiken mit Prometheus einsammeln

Konfiguriere Prometheus so, dass die `/prometheus`-Endpunkte der drei Service-Anwendungen
gescannt werden und die Metriken damit in Prometheus angefragt werden können. Orientiere dich
dabei an der "Getting Started" Dokumentation auf den Prometheus-Webseiten.

Diskutiert die folgenden Fragen:
* wie kann man in Prometheus nun die Durchsatzrate von versendeten E-Mails in den letzten 5 Minuten abfragen?
* wie kann man in Prometheus nun das 99te Perzentil der Antwortzeit von Warenkorb-Checkouts abfragen?


