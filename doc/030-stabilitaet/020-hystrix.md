# Hystrix-Dashboard-Monitoring
In dieser Übung wird für den ```fortune-cookie-product-service``` das Hystrix-Dashboard aktiviert. So kann die Ausführung eines Lasttests mit SoapUI mit dem Hystrix eigenen Dashboard analysiert werden.

Nimm dir für diese Aufgaben **30 Minuten** Zeit.

## Konfiguration

1. Erweitere die Klasse ProductServiceApplication (fortune-cookie-product-service) um die Annotation ```@EnableHystrix```. Mit der Annotation ```@EnableHystrix``` wird der Endpoint ```/hystrix.stream``` erzeugt. Dieser muss über einen definierbaren Management-Port angesprochen werden
2. Erweitere die Konfigurationsdatei (```application.yml```) des Service um einen geeigneten Management-Port und starte die Applikation neu
3. Downloade nun das Hystrix-Dashboard-Projekt
```
$ git clone https://github.com/Netflix/Hystrix.git
```
4. Starte anschließend das Dashboard via Gradle
```
$ cd Hystrix/hystrix-dashboard
$ ../gradlew jettyRun
```
5. Öffne das Hystrix-Dashboard unter http://localhost:7979/hystrix-dashboard/ und verbinde dich mit dem Hystrix-Stream
```
Single Hystrix App: http://hystrix-app:port/hystrix.stream 
z.B.:               http://localhost:8003/hystrix.stream
```
6. Starte zum Schluß mit SoapUI einen Lasttest auf den ```fortune-cookie-product-service``` und analysiere die Ausgabe des Dashboards (ggf. muss das Dashboard mit F5 neu geladen werden)

