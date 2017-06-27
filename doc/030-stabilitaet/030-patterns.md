# Den Shop stabilisieren

In dieser Übung werden die Services des Shops mit der Hilfe von Hystrix stabilisiert. Nimm dir für diese Aufgaben ca. **30 Minuten** Zeit.

**Relevante Bibliotheken:**
- com.netflix.hystrix:hystrix-core:1.5.10
- com.netflix.hystrix:hystrix-javanica:1.5.10
- org.springframework.cloud:spring-cloud-starter-hystrix:1.3.0.RELEASE


## Stabilisierung des fortune-cookie-product-service
Die Klasse FortuneCookieListGenerator lädt aus einer "simulierten" Quote-Datenbank eine konfigurierbare Menge von Quotes.
Antwortet das "Datenbanksystem" nicht oder zu langsam, werden nach und nach alle Threads der Anwendung blockiert.

### Circuit Breaker | Timeout | Fail Fast
In drei einfachen Schritten werden ihr die Logik des fehleranfälligen Service in ein HystrixCommand kapseln und damit gleich mehrere Stabilitäts-Pattern anwenden. 

1. Kapselt die Logik der Methode generateList() aus der Klasse FortuneCookieListGenerator in einem HystrixCommand (http://netflix.github.io/Hystrix/javadoc/index.html?com/netflix/hystrix/HystrixCommand.html). Beachtet dabei die geeignete Wahl eines Timeouts (executionIsolationThreadTimeoutinMilliseconds). Die notwendigen Bibliotheken müssen in der build.gradle Datei hinzugefügt werden.
2. Implementiert das Fail-Fast Pattern durch Überschreibender getFallback() Methode. Was wäre ein sinnvoller Fallback-Rückgabewert.
Hystrix unterstützt an dieser Stelle die Fail-Silent-Strategie, die bspw. durch Rückgabe von null oder einer leeren Liste umgesetzt wird. (Alternative Fallback-Methoden sind der Aufruf eines Backup Systems oder die Rückgabe gecachter Daten.)
3. Ruft das erstellte HystrixCommand im Controller synchron auf.
Alternativ kann die Ausführung als asynchroner Aufruf durch queue (Auswertung von Futures) oder als reaktiver Aufruf durch die observe-Methode (Implementierung des Observable-Patterns, RxJava) implementiert werden.

## Stabilisierung des fortune-cookie-mailing-service
Stabilisiert nun den fortune-cookie-mailing-service, in dem ihr die Logik des REST-Aufrufs "/send" aus der Klasse MailingController in einem HystrixCommand kapselt. Implementiert hier eine Fail-Silent Strategie und wählt geeignete Logging-Ausgaben.

## Stabilisierung des fortune-cookie-fulfillment-service
Stabilisiert nun den fortune-cookie-fulfillment-service mit Hystrix.

1. Kapselt die sendMail-Methode der Service-Klasse FulfillmentServiceService unter Verwendung der @HystrixCommand Annotation in einem HystrixCommand und legen eine geeignete Fallback-Methode, Gruppenschlüssel sowie geeignete Logging-Ausgaben fest.
2. Erweitert die Klasse FulfillmentServiceApplication um die Annotation @EnableHystrix.
Hystrix sucht in allen @Component und @Service annotierten Klassen nach @HystrixCommand annotierten Methoden.

3. Konfigurieren Sie ein passendes Timeout execution.isolation.thread.timeoutInMilliseconds innerhalb der Konfigurationsdatei der Anwendung. https://github.com/Netflix/Hystrix/wiki/Configuration