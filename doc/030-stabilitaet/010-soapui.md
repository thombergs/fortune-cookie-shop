# Lasttests mit SoapUI

In dieser Übugung wird SoapUI eingerichtet, damit die Anwendung eine Lasttest unterzogen werden kann. Nimm dir für diese Aufgaben ca. **30 Minuten** Zeit.

**SoapUI:** Tool für Tests von Webservices (REST, SOAP). Ermöglicht die Ausführung von Lasttests und ist verfügbar für Linux, MacOS und Windows. https://www.soapui.org/getting-started.html

## Installation und Ausführung
1. Installiert SoapUI und importieren die im Repo abgelegten Projektdateien
2. Startet alle relevanten Webanwendungen
3. Jetzt können einzelne Requests an die vorkonfigurierten Endpoints gesendet werden. Im Terminal der zum Server zugehörigen Session werden entsprechende Logging-Ausgaben angezeigt
4. Konfigurierte Lasttests können jetzt in den Projekten unter „Load Tests“ gefunden und ausgeführt werden

**Sollte der Fehler: Failed to load class “org.slf4j.impl.StaticLoggerBinder” auftreten:**
1.  slf4j http://www.slf4j.org/download.html laden
2.  die Datei slf4j-api im Verzeichnis SmartBear\SoapUI-5.2.1\lib löschen
3.  slf4j-api und slf4j-nop aus dem slf4j-Paket im Verzeichnis SmartBear\SoapUI-5.2.1\lib ablegen

**Sollte der Fehler: java.lang.ClassNotFoundException: com.eviware.soapui.plugins.auto.factories.AutoImportMethodFactory auftreten:**
1. Ordner .soapuios im Homeverzeichnis umbenennen, bspw. in tmp.soapuios