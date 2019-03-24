# Lasttests mit SoapUI
In dieser Übung wird SoapUI eingerichtet, damit die Anwendung einem Lasttest unterzogen werden kann. SoapUI ermöglicht, neben vielen anderen Funktionen, die Ausführung von Lasttests und ist für Linux, MacOS und Windows verfügbar.

Nimm dir für diese Aufgaben **15 Minuten** Zeit.

## Installation und Ausführung
1. Installiere und starte _SoapUI Open Source_ mit den Standardeinstellungen (https://www.soapui.org/getting-started.html)
2. Importiere die im Repo abgelegten Projektdateien (```/soapui-projects```)
   1. File > Import Project > Select File
3. Starte anschließend die Services des Cookie Shops
4. Sende nun einzelne Requests an die beiden vorkonfigurierten Endpoints
   1. Project > TestSuite > TestCase > Test Steps
5. Zum Ende führe noch die konfigurierten Lasttests aus
   1. Project > TestSuite > TestCase > Load Tests

Im Terminal der zum Service zugehörigen Session werden entsprechende Logging-Ausgaben der Requests angezeigt.

__Frage:__ Welcher Lasttest zeigt die höhere Fehlerrate in den Ergebnissen (rat) und was ist der Grund dafür?

## SoapUI Troubleshooting
**Sollte der Fehler ```Failed to load class “org.slf4j.impl.StaticLoggerBinder”``` auftreten:**
1.  slf4j von http://www.slf4j.org/download.html herunterladen
2.  Die Datei slf4j-api.jar im Verzeichnis SmartBear\SoapUI-5.x.x\lib löschen
3.  slf4j-api.jar und slf4j-nop.jar aus dem slf4j-Paket im Verzeichnis SmartBear\SoapUI-5.x.x\lib ablegen

**Sollte der Fehler ``java.lang.ClassNotFoundException: com.eviware.soapui.plugins.auto.factories.AutoImportMethodFactory`` auftreten:**
1. Ordner .soapuios im User-Home umbenennen (z.B. in tmp.soapuios)