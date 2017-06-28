# Lasttests mit SoapUI
In dieser Übugung wird SoapUI eingerichtet, damit die Anwendung eine Lasttest unterzogen werden kann. SoapUI ermöglicht, neben vielen anderen Funktionen, die Ausführung von Lasttests und ist für Linux, MacOS und Windows verfügbar.

Nimm dir für diese Aufgaben **15 Minuten** Zeit.

## Installation und Ausführung
1. Installiere und starte SoapUI (https://www.soapui.org/getting-started.html)
2. Extrahiere und importiere die im Repo abgelegten Projektdateien (```/soapui-projects```)
3. Starte nun alle relevanten Services
4. Sende nun einzelne Requests an die vorkonfigurierten Endpoints
5. Am Ende führe noch die konfigurierten Lasttests (unter „Load Tests“) aus

Im Terminal der zum Service zugehörigen Session werden entsprechende Logging-Ausgaben der Requests angezeigt. Was fällt dir auf?

## SoapUI Troubleshooting
**Sollte der Fehler ```Failed to load class “org.slf4j.impl.StaticLoggerBinder”``` auftreten:**
1.  slf4j von http://www.slf4j.org/download.html herunterladen
2.  Die Datei slf4j-api.jar im Verzeichnis SmartBear\SoapUI-5.2.1\lib löschen
3.  slf4j-api.jar und slf4j-nop.jar aus dem slf4j-Paket im Verzeichnis SmartBear\SoapUI-5.2.1\lib ablegen

**Sollte der Fehler ``java.lang.ClassNotFoundException: com.eviware.soapui.plugins.auto.factories.AutoImportMethodFactory`` auftreten:**
1. Ordner .soapuios im User-Home umbenennen (z.B. in tmp.soapuios)