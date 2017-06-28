# Caching anwenden

In dieser Übung wird die Beispielanwendung um einen Caching-Mechanimus erweitert, sowie eine Paging Funktion für den Product implementiert.

Nimm dir für diese Aufgaben **60 Minuten** Zeit.

## Einleitung
Für die Impelmentierung des Caching nutzen wir die Hystrix ``Request Cache``` Funktionalität. 

An welche Stelle der Anwendung würdest du die Anwendung eines Caching Meachnismus als sinnvoll erachten? 
- Treffe ggf. Annahmen um die Entscheidung zu Vereinfachen
- Diskutiere deine Entscheidung mit den anderen Teilnehmer
- An welcher Stelle der Anwendung wäre Caching sinnvoll und wo nicht (Frequenz?)

## Pagination
Der Cookie Service liefert zu Beginn alle vorhanden Cookies. Wieso kann das zu einem Problem werden?

Um dieses Problem zu beheben führe bitte eine Paginierung für die ```getList``` Methode der CookiesDB ein und nutze diese im Controller und der ```FortuneCookieDao``` Klasse.

```java
public ArrayList<FortuneCookieResource> getList(int offset, int limit) {
   ...
}
``` 

## Request Cache
Request caching kann bei ```HystrixCommand``` über die Implementierung der ```getCacheKey()```-Methode aktiviert werden.

Der Cache Key muss dabei eindeutig für die Anfrage sein. Die Kombination von Offset und Limit bieten sich hier als eindeutiger Schlüssel an.

```java
    @Override
    protected String getCacheKey() {
        return String.valueOf(offset) + "-" + String.valueOf(limit);
    }
```

Folge für die Implementierung der Anleitung unter: https://github.com/Netflix/Hystrix/wiki/How-To-Use#Caching

**Prüfe anschließend das Verhalten über die Lasttests bzw. einen Unit-Test.**