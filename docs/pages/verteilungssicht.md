# Deployment

## Momentaner Status

Die App ist momentan nur halb entwickelt und hat noch nicht viele Funktionalitäten.
Der momentane Stand kann allerdings mit Docker im Localhost ausprobiert werden.

## Übersicht Dcoker Compose

Der Befehl 
``docker compose up --build``
startet zwei Container:
- GwentApp: Hier befindet sich das Frontend und Backend
- MariaDB: Hier befindet sich die Datenbank

Das Frontend der GwentApp kann über ``localhost:8080`` erreicht werden