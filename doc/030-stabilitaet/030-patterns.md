# Den Shop stabilisieren
In dieser Übung werden die Services des Shops mit der Hilfe von Hystrix durch Stabilitäts-Patterns stabilisiert. 

Nimm dir für diese Aufgaben **45 Minuten** Zeit.

## Stabilisierung des fortune-cookie-product-service
Die Klasse FortuneCookieListGenerator lädt aus einer "simulierten" Quote-Datenbank eine konfigurierbare Menge von Quotes. Antwortet das "Datenbanksystem" nicht oder zu langsam, werden nach und nach alle Threads der Anwendung blockiert.

### Circuit Breaker | Timeout | Fail Fast
In drei einfachen Schritten wirst du die Logik des fehleranfälligen Service in ein HystrixCommand kapseln und damit gleich mehrere Stabilitäts-Pattern anwenden. 

1. Kapsel die Logik der Methode generateList() aus der Klasse FortuneCookieListGenerator in einem HystrixCommand (http://netflix.github.io/Hystrix/javadoc/index.html?com/netflix/hystrix/HystrixCommand.html). Beachte dabei die geeignete Wahl eines Timeouts (```executionIsolationThreadTimeoutinMilliseconds```).

Die folgenden Bibliotheken müssen in ```build.gradle``` als Abhängigkeit (dependencies) hinzugefügt werden:
```
    compile "com.netflix.hystrix:hystrix-core:1.5.10"
    compile "com.netflix.hystrix:hystrix-javanica:1.5.10"
    compile "org.springframework.cloud:spring-cloud-starter-hystrix:1.3.0.RELEASE"
```

2. Implementiere nun das Fail-Fast Pattern durch Überschreibender getFallback() Methode. Was wäre ein sinnvoller Fallback-Rückgabewert?

Hystrix unterstützt an dieser Stelle die Fail-Silent-Strategie, die bspw. durch Rückgabe von null oder einer leeren Liste umgesetzt wird. (Alternative Fallback-Methoden sind der Aufruf eines Backup Systems oder die Rückgabe gecachter Daten.)

3. Rufe jetzt das erstellte HystrixCommand im Controller synchron auf.

*Alternativ kann die Ausführung als asynchroner Aufruf durch queue (Auswertung von Futures) oder als reaktiver Aufruf durch die observe-Methode (Implementierung des Observable-Patterns, RxJava) implementiert werden.*

## Stabilisierung des fortune-cookie-mailing-service
Stabilisiere nun den ```fortune-cookie-mailing-service```, in dem ihr die Logik des REST-Aufrufs "/send" aus der Klasse MailingController in einem HystrixCommand kapselt. Implementiere hier eine Fail-Silent Strategie und wähle geeignete Logging-Ausgaben.

## Stabilisierung des fortune-cookie-fulfillment-service
Stabilisiere nun den ```fortune-cookie-fulfillment-service``` mit der ```@HystrixCommand```-Annotation.

1. Kapsel die sendMail-Methode der Service-Klasse FulfillmentServiceService unter Verwendung der ```@HystrixCommand```-Annotation in einem HystrixCommand und lege eine geeignete Fallback-Methode, Gruppenschlüssel sowie geeignete Logging-Ausgaben fest.

2. Erweitere die Klasse FulfillmentServiceApplication um die Annotation @EnableHystrix

*Hystrix sucht in allen @Component und @Service annotierten Klassen nach @HystrixCommand annotierten Methoden.*

3. Konfiguriere noch ein passendes Timeout mit der Definition von ```execution.isolation.thread.timeoutInMilliseconds``` innerhalb der Konfigurationsdatei der Anwendung (https://github.com/Netflix/Hystrix/wiki/Configuration)