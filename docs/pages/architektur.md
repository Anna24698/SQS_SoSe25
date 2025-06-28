# Architekturentscheidungen

## ADR 1: Entscheidung einen Deckbuilder für Gwent zu entwickeln

Status: Entschieden
Entschieden: 25.03.2025

Entscheidung: Ich habe entschieden einen Deckbuilder zu entwickeln.

Begründung:

- Momentanes Interesse an Gwent
- Kontrolle über die Funktionalitäten der App
- Offlinemöglichkeit mancher Funktionalitäten


Alternativen:
- https://www.playgwent.com/de/decks/builder/create-new/factions-and-abilities
- Das Steamspiel Gwent


## ADR 2: Entscheidung für MariaDB als Datenbank

Status: Entschieden
Entschieden: 29.03.2025

Entscheidung: Ich habe entschieden MariaDB als Datenbank zu verwenden.

Begründung:

- geringer Speicherbedarf
- Datentyp-Flexibilität
- im schlimmsten Fall einfacher Wechsel auf MySQL möglich
- einfaches Setup

Alternativen:

- MySQL
- PostgreSQL
- ...

## ADR 3: Thymeleaf fürs Frontend

Status: Entschieden
Entschieden: 25.03.2025

Entscheidung: Ich habe entschieden Thymeleaf fürs Frontend zu verwenden.

Begründung:

- einfacher Einbau ins Projekt
- gute Zusammenarbeit mit Spring
- leistungsfähig
- wartbar


Alternativen:

- React
- Freemarker