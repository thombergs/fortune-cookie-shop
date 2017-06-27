# Prometheus kennenlernen 

Nimm dir für die folgenden Aufgaben ca. **30 Minuten** Zeit.

## Prometheus installieren

Unter https://prometheus.io/download/ die Version 1.7.1 von
Prometheus herunterladen und in einem Verzeichnis Deiner Wahl
entpacken.

## Getting Started

Folge dem offiziellen "Getting Started" Guide von Prometheus unter 
(https://prometheus.io/docs/introduction/getting_started/)[https://prometheus.io/docs/introduction/getting_started/]
bis einschl. zum Absatz "Using the expression browser".

Im Getting Started Guide wirst du einige Metriken abfragen, die der Prometheus-Server selbst veröffentlicht.

## Expressions

Spiele etwas mit der expression language herum, um einige Metriken abzufragen (siehe (https://prometheus.io/docs/querying/basics/)[https://prometheus.io/docs/querying/basics/]).

Beantworte die folgenden Fragen:

* Was ist ein "Instant Vector"?
* Was ist ein "Range Vector"?
* Was ist die Bedeutung der folgenden Expression?
  `rate(http_requests_total{method="get", code="200"}[5m])`
