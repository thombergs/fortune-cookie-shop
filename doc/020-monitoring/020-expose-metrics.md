# Metriken in die Beispielanwendung einbauen

In dieser Übung wird die Beispielanwendung erweitert, um einige Metriken zu erfassen
und diese über eine Schnittstelle zur Verfügung zu stellen. Nimm dir für diese
Aufgaben ca. **60 Minuten** Zeit.

## Metriken erfassen

Für die Erfassung der Metriken nutzen wir den "Simple Client" für Java von Prometheus. 

Füge die folgenden Metriken (und ggf. weitere, die du für sinnvoll erachtest) in 
die Server-Anwendungen ein. Nutze als Grundlage das untenstehende Code-Beispiel.

* Durchsatz von Warenkorb-Checkouts pro Sekunde
* Durchsatz von versendeten Mails pro Sekunde
* Durchsatz von Produkt-Listing-Anfragen
* Antwortzeiten von Warenkorb-Checkouts
* Antwortzeiten des Mailversands
* Antwortzeiten von Produkt-Listing-Anfragen

**Beispiel-Code zum Einbinden eines Counters:**

```java
public class MyService {

  private Counter counter;

  public MailService() {
    this.counter = Counter.build()
            .name("my_counter")
            .help("counts invocations of doSomething()")
            .register();
  }

  public void doSomething() {
    counter.inc();
  }
}
```

**Beispiel-Code zum Einbinden eines Timers:**

```java
public class MyService {

  private Histogram timer;

  public MyService() {
    this.timer = Histogram.build()
            .buckets(.01, .025, .05, .075, .1, .2, .3, .4, .5, .6, .7, .8, .9, 1, 1.5, 2, 2.5, 5, 7.5, 10)
            .name("duration_in_seconds")
            .labelNames("endpoint")
            .help("duration of an HTTP call")
            .register();
  }

  public void doSomething() {
    Histogram.Timer timer = this.timer
            .labels("/my_endpoint")
            .startTimer();
    try {

      // do something
    } finally {
      timer.observeDuration();
    }
  }

}
```

**Beantworte dabei die folgenden Fragen:**

* an welchen Stellen ist es am sinnvollsten, die Erfassung der Metriken einzubauen? (Rest-Controller? Service-Schicht?)
* wie könnte man die Fehlerrate eines Service als Metrik erfassen?

## Metriken im Prometheus-Format bereitstellen

Die Metriken liegen bisher nur im Speicher der Anwendungen vor. Damit sie von Prometheus gescraped werden können,
müssen sie in das Prometheus-Datenformat übertragen und per HTTP bereitgestellt werden. Baue hierfür die folgende Klasse in 
jede der drei Service-Anwendungen ein:

```java
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
```

Prüfe, ob die Metriken über den Endpoint `/prometheus` im Prometheus-Format angeboten werden. Die URLs für die
verschiedenen Server sind die folgenden:

* **fulfillment-service:** http://localhost:8081/prometheus
* **mailing-service:** http://localhost:8082/prometheus
* **product-service:** http://localhost:8083/prometheus

## Metriken mit Prometheus einsammeln

Konfiguriere Prometheus so, dass die `/prometheus`-Endpunkte der drei Service-Anwendungen
gescannt werden und die Metriken damit in Prometheus angefragt werden können. Orientiere dich
dabei an der "Getting Started" Dokumentation auf den Prometheus-Webseiten.

Diskutiert die folgenden Fragen:
* wie kann man in Prometheus nun die Durchsatzrate von versendeten E-Mails in den letzten 5 Minuten abfragen?
* wie kann man in Prometheus nun das 99te Perzentil der Antwortzeit von Warenkorb-Checkouts abfragen?


