# Den Cookie Shop stabilisieren
In dieser Übung werden die Services des Shops mit der Hilfe von Resilience4j durch Stabilitäts-Patterns stabilisiert. 

Nimm dir für diese Aufgaben **45 Minuten** Zeit.

## Stabilisierung des fortune-cookie-mailing-service
Der `MailService` simuliert den Versand einer Mail. Antwortet der Service nicht oder zu langsam, so werden nach und nach alle Threads der Anwendung blockiert.

In dieser Aufgabe wirst du in zwei einfachen Schritten die Logik des fehleranfälligen Service mit Resilience4j absichern.

### Resilience4j Module
Die Resilience4j-Library bietet folgende Kernmodule an (siehe [Dokumentation](http://resilience4j.github.io/resilience4j/#_usage_guide)):
- `resilience4j-circuitbreaker`: Circuit breaking
- `resilience4j-ratelimiter`: Rate limiting
- `resilience4j-bulkhead`: Bulkheading
- `resilience4j-retry`: Automatic retrying
- `resilience4j-cache`: Response caching
- `resilience4j-timelimiter`: Timeout handling

Für unseren fehleranfälligen Service machen die Module `retry` und `timelimiter` Sinn, also füge diese zu den Dependencies hinzu.

### Retry für `sendMail()`
In dieser Aufgabe sollst du den `fortune-cookie-mailing-service` stabilisierung, indem ihr die Logik der `sendMail(MailResource)`-Methode aus der Klasse MailService mit einem Retry absichert. Retries führen eine Funktion bei einem Fehlerfall erneut aus solange die vorgegebene Anzahl der Versuche nicht überschritten wurde. Dazu verwendet Resilience4j das Decorator-Pattern:
```java
// Aus dem Funktionsausruf ein Runnable machen
Runnable function = ...;

// Retry-Strategie konfigurieren
RetryConfig retryConfig = RetryConfig.custom()
        .maxAttempts(5)                       // 5 Versuche
        .waitDuration(Duration.ofMillis(20))  // Zwischen jedem Versuch 20ms warten
        .build();
Retry retry = Retry.of("someName", retryConfig);

// Runnable "dekorieren"
Runnable decoratedFunction = Retry.decorateRunnable(retry, function);

// Aufruf
decoratedFunction.run();
```
Der Aufruf wird bis zu 5 mal wiederholt ehedem das Runnable "aufgibt".
### TimeLimiter
Auf ganz ähnliche Weise kann ein TimeLimiter erstellt werden.
TimeLimiter kapseln ein Future und brechen nach einer voreingestellten Dauer den Aufruf ab.
Hierfür wird ebenfalls das Decorator-Pattern verwendet:
```java
// Aus dem Funktionsaufruf ein Future machen
Future future = Executors.newSingleThreadExecutor().submit(function);

// TimeLimiter konfigurieren
TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
        .timeoutDuration(Duration.ofMillis(4500)) // Timeout bei 4500ms
        .cancelRunningFuture(true)                // Das Future soll gecancelt werden
        .build();
TimeLimiter timeLimiter = TimeLimiter.of(timeLimiterConfig);

// Supplier<Future> mit timeLimiter "dekorieren"
Callable decoratedFutureSupplier = TimeLimiter.decorateFutureSupplier(timeLimiter, () -> future);
```
Versuche, die folgenden Fragen zu beantworten: Wie müssen Retry und TimeLimiter miteinander kombiniert werden, um Anfragen an den fehleranfälligen Service abzusichern?
Wie muss der Code aussehen, wenn auf einen Rückgabewert gewartet werden muss?
Wie lässt sich der Code wiederverwendbar machen?

## Stabilisierung des fortune-cookie-product-service
Der `FortuneCookieService` lädt aus einer "simulierten" Cookies-Datenbank alle vorhandenen Cookies (30). Antwortet der Service bzw. die Datenbank nicht oder zu langsam, werden nach und nach alle Threads der Anwendung blockiert.

### Kapselung mittels Circuit Breaker
In diesem Abschnitt wirst du den fehleranfällige ProductService mit dem Circuit-Breaker-Pattern stabilisieren.
Denke daran geeignete Logging-Ausgaben festzulegen.
Füge, falls noch nicht geschehen, die Resilience4j-Abhängigkeiten für `CircuitBreaker` und `TimeLimiter` der `build.gradle` von `fortune-cookie-product-service` hinzu.

Jetzt kapsel die Logik der Methode `getCookies()` aus der Klasse `FortuneCookieService` mit einem `CircuitBreaker` und einem `TimeLimiter`. Circuit Breaker können auf folgende Weise erstellt werden:
```java
CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
        .failureRateThreshold(50) // %-Rate der fehlschlagenden Versuche, ab der der CircuitBreaker in den OPEN-Zustand wechselt
        .ringBufferSizeInClosedState(10) // Wieviele Versuche bei der Berechnung der Rate im CLOSED-Zustand in Betracht gezogen werden
        .ringBufferSizeInHalfOpenState(10) // Wie oben, nur für den HALF-OPEN-Zustand
        .waitDurationInOpenState(Duration.ofSeconds(20)) // Wie lange im OPEN-Zustand verharrt wird bevor in den HALF-OPEN-Zustand gewechselt wird
        .enableAutomaticTransitionFromOpenToHalfOpen()
        .build();
CircuitBreaker customCircuitBreaker = CircuitBreaker.of("MyCircuitBreaker", circuitBreakerConfig);
Callable circuitBreakerCallable = CircuitBreaker.decorateCallable(circuitBreaker, myCallable);
```
Versuche, die folgenden Fragen zu beantworten: Wo muss der Curcuit Breaker instantiiert werden? Wie werden der Time Limiter und der Curcuit Breaker miteinander verbunden? Wie lange sollte der Circuit Breaker im OPEN-Zustand verweilen?

## Stabilisierung des fortune-cookie-fulfillment-service
Stabilisiere nun den `fortune-cookie-fulfillment-service`. Die Stabilisierung kann analog zu der Stabilisierung des `fortune-cookie-product-service` durchgeführt werden.

Zu stabilisieren ist dabei die `sendMail`-Methode der Service-Klasse FulfillmentServiceService. Sie erzeugt ein MailResource objekt und versucht dann den Versand über den `fortune-cookie-mailing-service`. 

## Test
Führe nun die Lastest aus und diskutiert wie sich das Verhalten der Anwendung geändert hat.