# Hystrix-Dashboard-Monitoring
In dieser Übung wird für den fortune-cookie-product-service das Hystrix-Dashboard aktiviert. So kann die Ausführung eines Lasttests mit SoapUI mit dem Hystrix eigenen Dashboard analysiert werden.  Nimm dir für diese Aufgaben ca. **30 Minuten** Zeit.

## Konfigurieren 

1. Erweitert die Klasse ProductServiceApplication um die Annotation @EnableHystrix.
Mit der Annotation @EnableHystrix wird automatisch der Endpoint /hystrix.stream hinzugefügt. Dieser muss über einen speziellen Management-Port angesprochen werden.
2. Erweitert die Konfigurationsdatei um einen geeigneten Management-Port und startet die Applikation neu.
3. Download und Ausführen des Hystrix-Dashboard-Projektes von Github. https://github.com/Netflix/Hystrix/wiki/Dashboard. Empfohlen wird die Ausführung via Gradle (siehe Github-Link).
4. Öffnet das Hystrix-Dashboard unter http://localhost:7979/hystrix-dashboard/ und verbinden Sie sich mit dem Stream.
5. Startet einen Lasttest und analysiert die Ausgabe.