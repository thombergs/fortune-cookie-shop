# Entwicklungsumgebung einrichten

## JDK
Es muss ein JDK 8 installiert sein.

## 7zip
Zum Entpacken einiger Downloads wird 7zip benötigt.

## Node JS
Für das Starten der Angular App wird Node JS benötigt. Bitte die
LTS Version 6.11.0 unter https://nodejs.org/en/download/ herunterladen und installieren.

Zum Testen können die folgenden Befehle in einer Konsole ausgeführt werden. Sie müssten jeweils eine Versionsnummer ausgeben:

```
node -v
npm -v
```

## IDE
Es wird eine IDE benötigt, um mit dem Quellcode der Beispielanwendung arbeiten zu können.
Empfohlen wird IntelliJ, Du kannst aber die IDE deiner Wahl nutzen (wenn es nicht IntelliJ ist, können wir aber nur bedingt bei Problemen helfen ;)).

## Quellcode auschecken
Der Quellcode der Beispielanwendung liegt auf GitHub und kann unter der URL
`https://github.com/thombergs/fortune-cookie-shop.git` mit einem Git clone Befehl ausgecheckt werden.

Du kannst den Code entweder über die IDE auschecken oder über die Git-Console 
(die dann ggf. auch noch heruntergeladen und installiert werden muss: https://git-scm.com/downloads).

Wenn der Code ausgecheckt ist, importiere ihn als Gradle-Projekt in deine IDE.

## Abhängigkeiten der Angular App herunterladen
Initial im Ordner /fortune-cookie-app einmal `npm install` ausführen, um die Abhängigkeiten der Angular App aus dem Netz zu laden. Dies kann eine Weile dauern.

Weiterhin muss einmal der Befehl `npm install -g @angular/cli` ausgeführt werden, damit man Angular CLI nutzen kann, um die
Anwendung im Entwicklermodus zu starten.

## Server-Anwendungen starten
Im Hauptordner des Projekts die folgenden Befehle in jeweils einer eigenen Konsole ausführen, um die Anwendungen zu starten
(unter Unix oder eine Unix-ähnlichen Shell wie Git Bash oder Cygwin muss `./gradlew` statt `gradlew.bat` verwendet werden):

```
gradlew.bat fortune-cookie-edge-server:bootrun
gradlew.bat fortune-cookie-fulfillment-service:bootrun
gradlew.bat fortune-cookie-product-service:bootrun
gradlew.bat fortune-cookie-mailing-service:bootrun
```

## Angular App starten
Im Ordner `fortune-cookie-app` den Befehl `ng serve` ausführen, um die Angular App zu starten. 

## Anwendung im Browser testen

Wenn alle Serveranwendungen und die Angular App gestartet sind, kann die Anwendung über `http://localhost:8000`
im Browser getestet werden.



