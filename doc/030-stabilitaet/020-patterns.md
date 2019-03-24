# Den Cookie Shop stabilisieren
In dieser Übung werden die Services des Shops mit der Hilfe von Hystrix durch Stabilitäts-Pattern stabilisiert. 

Nimm dir für diese Aufgaben **45 Minuten** Zeit.

## Stabilisierung des fortune-cookie-mailing-service
Der ```MailService``` simuliert den Versand einer Mail. Antwortet der Service nicht oder zu langsam, so werden nach und nach alle Threads der Anwendung blockiert.

In dieser Aufgabe wirst du in drei einfachen Schritten die Logik des fehleranfälligen Service in ein HystrixCommand kapseln und damit gleich mehrere Stabilitäts-Pattern anwenden.

**Siehe:** https://github.com/Netflix/Hystrix/wiki/How-To-Use#Hello-World **und** http://netflix.github.io/Hystrix/javadoc/index.html?com/netflix/hystrix/HystrixCommand.html

### Kapselung in ein HystrixCommand
In dieser Aufgabe sollst du den ```fortune-cookie-mailing-service``` stabilisieren, in dem du die Logik der ```sendMail(MailResource mail)```-Methode aus der Klasse MailService in ein HystrixCommand kapselst. Implementiere zudem eine Fail-Silent Strategie und wähle geeignete Logging-Ausgaben.

1. Kapsel die Logik der Methode ```sendMail(MailResource mail)``` aus der Klasse ```MailService``` in einem HystrixCommand. Beachte dabei die geeignete Wahl eines Timeouts (```executionIsolationThreadTimeoutinMilliseconds```). 

Die folgenden Bibliotheken müssen in ```build.gradle``` als Abhängigkeit (dependencies) hinzugefügt werden:
```
    compile "com.netflix.hystrix:hystrix-core:1.5.18"
    compile "com.netflix.hystrix:hystrix-javanica:1.5.18"
    compile "org.springframework.cloud:spring-cloud-starter-hystrix:1.3.0.RELEASE"
```

Setze zudem einen GroupKey in dem Constructor der HystrixCommand-Klasse.
```java
    super(HystrixCommandGroupKey.Factory.asKey("MailServiceGroup"), 4500);
```

2. Implementiere nun das Fail-Fast Pattern als Fail-Silent, indem du die ```getFallback()```-Methode überschreibst. Was wäre ein sinnvoller Fallback-Rückgabewert?

Hystrix unterstützt an dieser Stelle die Fail-Silent-Strategie, die bspw. durch Rückgabe von ```null``` oder einer leeren Liste umgesetzt wird. (Alternative Fallback-Methoden sind der Aufruf eines Backup Systems oder die Rückgabe gecachter Daten.)

3. Rufe abschließend das erstellte HystrixCommand im Controller synchron auf.

__Hinweis:__ Das HystrixCommand definiert über ein Generic der _Return Type_ (```HystrixCommand<R>```). Um das Problem des fehlenden Rückgabewertes zu umgehen kannst du die Methode so umschreiben, dass ein Wert (z.B. String) zurückgegeben wird oder mit dem Typ ```Void``` und ```return null``` eine Rückgabe "unterbinden".

## Stabilisierung des fortune-cookie-product-service
Der ```FortuneCookieService``` lädt aus einer "simulierten" Cookies-Datenbank alle vorhandenen Cookies (30). Antwortet der Service bzw. die Datenbank nicht oder zu langsam, werden nach und nach alle Threads der Anwendung blockiert.

### Absicherung mit der @HystrixCommand-Annotation
In sechs einfachen Schritten wirst du die Logik des fehleranfälligen Service  über die ```@HystrixCommand```-Annotation in ein HystrixCommand kapseln und damit gleich mehrere Stabilitäts-Pattern anwenden. Denke daran geeignete Logging-Ausgaben zu implementieren.

**Siehe dazu:** https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-javanica

1. Erweitere die Klasse ```ProductServiceApplication``` um die Annotation ```@EnableHystrix``` (sofern noch nicht geschehen).

*Hystrix sucht in allen @Component und @Service annotierten Klassen nach @HystrixCommand annotierten Methoden.*

Die folgenden Bibliotheken müssen in ```build.gradle``` als Abhängigkeit (dependencies) hinzugefügt werden:
```
    compile "com.netflix.hystrix:hystrix-core:1.5.18"
    compile "com.netflix.hystrix:hystrix-javanica:1.5.18"
    compile "org.springframework.cloud:spring-cloud-starter-hystrix:1.3.0.RELEASE"
```

2. Kapsel die Logik der Methode ```getCookies()``` aus der Klasse ```FortuneCookieService``` in einem HystrixCommand. Nutze dabei die ```@HystrixCommand```-Annotation aus der ```hystrix-javanica```-Bibliothek. 

3. Definiert über die Annotation die Fallback-Methode und den GroupKey.

4. Implementiere nun das Fail-Fast Pattern mit Hilfe der ```getCookiesFallback```-Methode. Was wäre ein sinnvoller Fallback-Rückgabewert?

```java
    public ArrayList<FortuneCookieResource> getCookiesFallback() {
```

5. Konfiguriere noch ein passendes Timeout mit der Definition von ```execution.isolation.thread.timeoutInMilliseconds``` innerhalb der Konfigurationsdatei der Anwendung (https://github.com/Netflix/Hystrix/wiki/Configuration)

```
hystrix:
  command:
    default:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 4500
```

6. Rufe jetzt das erstellte HystrixCommand im Controller synchron auf.

## Stabilisierung des fortune-cookie-fulfillment-service
Stabilisiere nun den ```fortune-cookie-fulfillment-service``` mit der ```@HystrixCommand```-Annotation. Die Stabilisierung kann analog zu der Stabilisierung des ```fortune-cookie-product-service``` durchgeführt werden.

Zu stabilisieren ist dabei die ```sendMail```-Methode der Service-Klasse FulfillmentServiceService. Sie erzeugt ein MailResource objekt und versucht dann den Versand über den ```fortune-cookie-mailing-service``. 

## Test
Führe nun die Lastest aus und diskutiert wie sich das Verhalten der Anwendung geändert hat.

__Frage:__ Wir hat sich die Fehlerrate (rat) zum initialen Lasttest verändert?
