# Hystrix-Dashboard-Monitoring
In dieser Übung wird für den ```fortune-cookie-product-service``` das Hystrix-Dashboard aktiviert. So kann die Ausführung eines Lasttests mit SoapUI mit dem Hystrix eigenen Dashboard analysiert werden.

Nimm dir für diese Aufgaben **30 Minuten** Zeit.

## Konfiguration

1. Erweitere die Klasse ProductServiceApplication (fortune-cookie-product-service) um die Annotation ```@EnableHystrix``` (sofern noch nicht geschehen). Mit der Annotation ```@EnableHystrix``` wird der Endpoint ```/hystrix.stream``` erzeugt. 
 
   Füge dafür die folgenden Imports zur ```build.gradle``` hinzu:
   ```
   compile "com.netflix.hystrix:hystrix-core:1.5.10"
   compile "com.netflix.hystrix:hystrix-javanica:1.5.10"
   compile "org.springframework.cloud:spring-cloud-starter-hystrix:1.3.0.RELEASE"
   ```
2. Der Hystrix-Endpoint muss über einen definierbaren Management-Port angesprochen werden. Erweitere dafür die Konfigurationsdatei (```application.yml```) des Service um einen geeigneten Management-Port und starte die Applikation neu. z.B.:
   ```
   management:
     port: 8003
   ```
3. Downloade nun das Hystrix-Dashboard-Projekt für den git-Hash ```dbc7429dbf7b82b4f27610f0bfc2e187106179ad```. Die ältere Version wird benötigt, da das Dashboard nicht mehr Bestandteil von Hystrix ist.
   ```
   $ git clone https://github.com/Netflix/Hystrix.git
   $ cd Hystrix
   $ git checkout dbc7429dbf7b82b4f27610f0bfc2e187106179ad
   ```
4. Starte anschließend das Dashboard via Gradle
   ```
   $ cd hystrix-dashboard
   $ ../gradlew jettyRun
   ```
5. Öffne das Hystrix-Dashboard unter http://localhost:7979/hystrix-dashboard/ und verbinde dich mit dem Hystrix-Stream
   ```
   Single Hystrix App: http://hystrix-app:port/hystrix.stream 
   z.B.:               http://localhost:8003/hystrix.stream
   ```
6. Starte abschließend mit SoapUI einen Lasttest auf den ```fortune-cookie-product-service``` und analysiere die Ausgabe des Dashboards (ggf. muss das Dashboard mit F5 neu geladen werden)

