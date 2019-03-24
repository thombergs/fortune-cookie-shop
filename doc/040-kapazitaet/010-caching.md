# Caching anwenden
In dieser Übung wird die Beispielanwendung um einen Caching-Mechanismus erweitert.

Nimm dir für diese Aufgaben **60 Minuten** Zeit.

## Einleitung
Für die Implementierung des Caching werden wir die Spring Cache Funktionalität (https://spring.io/guides/gs/caching/) nutzen.

An welcher/welchen Stelle(n) der Services würdest du die Anwendung eines Caching-Mechanismus als sinnvoll erachten? 
- Treffe ggf. Annahmen um die Entscheidung zu vereinfachen
- Diskutiere deine Entscheidung mit den anderen Teilnehmer
- An welcher Stelle der Anwendung wäre Caching sinnvoll und wo nicht

## Pagination
Der Controller des ```fortune-cookie-product-service``` ist nicht parametrisierbar und liefert zu Beginn alle vorhandenen Cookies. Wieso kann das zu einem Problem (besonders in Verbindung mit einem Cache) führen?

1. Führe, um das Problem zu beheben, eine Paginierung für die ```getList``` Methode der ```CookiesDB```-Klasse ein und nutze diese im zugehörigen Controller und der ```FortuneCookieDao```-Klasse.

```java
public ArrayList<FortuneCookieResource> getList(int offset, int limit) {
   ...
}
``` 

*Die Anpassung der Controller-Methode kann entfallen, da diese zudem eine Anpassung der Angular-App erfordern würde.*

## Caching Data with Spring
1. Füge die benötigten Abhängikeiten hinzu (```build.gradle```)

```
dependencies {
    ...
    compile "org.springframework.boot:spring-boot-starter-cache:${version_spring_boot}"
```

2. Initialisiere den Caching Mechanismus für Spring Boot mit Hilfe der ```@EnableCaching-Annotation``` in der Klasse ProductServiceApplication. Implementiere in der selben Klasse noch einen Producer für den default CacheManager.

```java
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("cookies");
    }
```

3. Annotiere das HystrixCommand ```getCookies```mit der ```@Cacheable```-Annotation. 

```java
       @Cacheable(value = "cookies")
```

4. Führe anschließend den SoapUI Lasttest für den Cookies Service aus. Was kannst du beobachten?
