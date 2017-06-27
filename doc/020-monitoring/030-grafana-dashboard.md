# Grafana-Dashboard einrichten

In dieser Übung wird ein Dashboard eingerichtet, mit dem die Metriken
aus Prometheus visualisiert werden können. Nimm dir für diese Aufgaben
ca. **30 Minuten** Zeit.

## Grafana installieren

Grafana in Version 4.3.2 von (https://grafana.com/grafana/download)[https://grafana.com/grafana/download]
herunterladen und an einer beliebigen Stelle entpacken.

## Prometheus als Datenquelle einrichten

Folge der Anleitung unter (https://prometheus.io/docs/visualization/grafana/)[https://prometheus.io/docs/visualization/grafana/],
um Prometheus als Datenquelle einzurichten und ein beliebiges Beispiel-Diagramm aus den Daten zu erstellen.

## Dashboard konfigurieren

Konfiguriere ein Dashboard mit mindestens den folgenden Metriken:

* Durchsatz mindestens eines Service
* Antwortzeiten mindestens eines Service
* Aktuell verbrauchter und verfügbarer Heap-Space mindestens eines Service

Fallen Dir noch andere Metriken ein, die in einem solchen Dashboard sinnvollerweise angezeigt werden könnten?