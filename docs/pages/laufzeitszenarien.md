## Laufzeitszenarien

(In den Details findet sich der UML-Code, der auf verschiedenen Webseiten,
wie [dieser](https://www.planttext.com/) überarbeitet und angepasst werden kann)

1. Nutzer registriert sich

![Laufzeitszenario_1.png](..%2FBilder%2FLaufzeitszenario_1.png)

<details>

@startuml

Nutzer -> Frontend: Gibt Nutzerdaten ein und versucht zu registrieren

Frontend -> Backend: Gibt Nutzerdaten weiter

Backend -> MariaDB: existiert Nutzername bereits?

MariaDB -> Backend: Nein

Backend -> MariaDB: Speichere neuen Nutzer ab

Backend -> Frontend: Weiterleiten auf Login

Frontend -> Nutzer: Zeige Loginseite



Nutzer -> Frontend: Gibt Nutzerdaten ein und versucht zu registrieren

Frontend -> Backend: Gibt Nutzerdaten weiter

Backend -> MariaDB: existiert Nutzername bereits?

MariaDB -> Backend: Ja

Backend -> Frontend: Gibt Fehler weiter

Frontend -> Nutzer: Zeige Registrierungsseite mit Fehler

@enduml
</details>

2. Nutzer logged sich ein

![Laufzeitszenario_2.png](..%2FBilder%2FLaufzeitszenario_2.png)

<details>
@startuml



Nutzer -> Frontend: Gibt Nutzerdaten ein und versucht anzumelden

Frontend -> Backend: Gibt Nutzerdaten weiter

Backend -> MariaDB: existiert Nutzername

MariaDB -> Backend: Nein


Backend -> Frontend: Gibt Fehler weiter

Frontend -> Nutzer: Zeige Loginseite



Nutzer -> Frontend: Gibt Nutzerdaten ein und versucht anzumelden

Frontend -> Backend: Gibt Nutzerdaten weiter

Backend -> MariaDB: existiert Nutzername

MariaDB -> Backend: Ja

Backend -> MariaDB: Passt das Passwort?

MariaDB -> Backend: Ja

Backend -> Frontend: Weiterleiten auf Deckbauseite

Frontend -> Nutzer: Zeige Deckbauseite



Nutzer -> Frontend: Gibt Nutzerdaten ein und versucht anzumelden

Frontend -> Backend: Gibt Nutzerdaten weiter

Backend -> MariaDB: existiert Nutzername

MariaDB -> Backend: Ja

Backend -> MariaDB: Passt das Passwort?

MariaDB -> Backend: Nein

Backend -> Frontend: Gibt Fehler weiter

Frontend -> Nutzer: Zeige Loginseite

@enduml
</details>


3. Nutzer logged sich aus

![Laufzeitszenario_3.png](..%2FBilder%2FLaufzeitszenario_3.png)

<details>
@startuml



Nutzer -> Frontend: Drückt auf Auslogg-Button

Frontend -> Backend: Logged aus



Backend -> Frontend: Leitet auf Deckbauseite

Frontend -> Nutzer: Zeige Deckbauseite




@enduml
</details>


4. Nutzer stellt ein Deck zusammen (unangemeldet)

![Laufzeitszenario_4.png](..%2FBilder%2FLaufzeitszenario_4.png)


<details>
@startuml



Nutzer -> Frontend: Startet Deckbauseite

Frontend -> Backend: Gibt den Start weiter

Backend -> MariaDB: Fordert Kartendaten

MariaDB -> Backend: Gibt Kartendaten zurück

Backend -> GwentAPI: Fordert Kartenbilder

GwentAPI -> Backend: Gibt Kartenbilder zurück


Backend -> Frontend: Gibt Daten weiter

Frontend -> Nutzer: Zeige Deckbauseite

Nutzer -> Frontend: wählt Karte aus

Frontend -> Nutzer: zeigt Karte in Deck

Nutzer -> Frontend: Wählt Anführer aus

Frontend -> Backend:  Weiterleiten auf Deckbauseite mit Anführer

Backend -> MariaDB: Fordert Kartendaten

MariaDB -> Backend: Gibt Kartendaten zurück

Backend -> GwentAPI: Fordert Kartenbilder

GwentAPI -> Backend: Gibt Kartenbilder zurück


Backend -> Frontend: Gibt Daten weiter

Frontend -> Nutzer: Zeige Deckbauseite

Nutzer -> Frontend: wählt Karte aus

Frontend -> Nutzer: zeigt Karte in Deck


@enduml
</details>


5. Nutzer stellt ein Deck zusammen (angemeldet) und speichert dieses

Nutzer hat noch kein Deck:

![Laufzeitszenario_5_1.png](..%2FBilder%2FLaufzeitszenario_5_1.png)

<details>
@startuml



Nutzer -> Frontend: Startet Deckbauseite

Frontend -> Backend: Gibt den Start weiter

Backend -> MariaDB: existiert ein Deck?

MariaDB -> Backend: Nein

Backend -> MariaDB: Fordert Kartendaten

MariaDB -> Backend: Gibt Kartendaten zurück

Backend -> GwentAPI: Fordert Kartenbilder

GwentAPI -> Backend: Gibt Kartenbilder zurück


Backend -> Frontend: Gibt Daten weiter

Frontend -> Nutzer: Zeige Deckbauseite

Nutzer -> Frontend: wählt Karte aus

Frontend -> Nutzer: zeigt Karte in Deck

Nutzer -> Frontend: Wählt Anführer aus

Frontend -> Backend:  Weiterleiten auf Deckbauseite mit Anführer

Backend -> MariaDB: Fordert Kartendaten

MariaDB -> Backend: Gibt Kartendaten zurück

Backend -> GwentAPI: Fordert Kartenbilder

GwentAPI -> Backend: Gibt Kartenbilder zurück


Backend -> Frontend: Gibt Daten weiter

Frontend -> Nutzer: Zeige Deckbauseite

Nutzer -> Frontend: wählt Karte aus

Frontend -> Nutzer: zeigt Karte in Deck

Nutzer -> Frontend: Drückt speichern Button

Frontend -> Backend: Gibt Deckdaten weiter

Backend -> MariaDB: Speichert Deck ab



@enduml

</details>

Nutzer hat bereits ein Deck:

![Laufzeitszenario_5_2.png](..%2FBilder%2FLaufzeitszenario_5_2.png)

<details>
@startuml



Nutzer -> Frontend: Startet Deckbauseite

Frontend -> Backend: Gibt den Start weiter

Backend -> MariaDB: existiert ein Deck?

MariaDB -> Backend: Ja

Backend -> MariaDB: Fordert Kartendaten und Deckdaten

MariaDB -> Backend: Gibt Kartendaten und Deckdaten zurück

Backend -> GwentAPI: Fordert Kartenbilder

GwentAPI -> Backend: Gibt Kartenbilder zurück


Backend -> Frontend: Gibt Daten weiter

Frontend -> Nutzer: Zeige Deckbauseite

Nutzer -> Frontend: wählt Karte aus

Frontend -> Nutzer: zeigt Karte in Deck

Nutzer -> Frontend: Wählt Anführer aus

Frontend -> Backend:  Weiterleiten auf Deckbauseite mit Anführer

Backend -> MariaDB: Fordert Kartendaten

MariaDB -> Backend: Gibt Kartendaten zurück

Backend -> GwentAPI: Fordert Kartenbilder

GwentAPI -> Backend: Gibt Kartenbilder zurück


Backend -> Frontend: Gibt Daten weiter

Frontend -> Nutzer: Zeige Deckbauseite

Nutzer -> Frontend: wählt Karte aus

Frontend -> Nutzer: zeigt Karte in Deck

Nutzer -> Frontend: Drückt speichern Button

Frontend -> Backend: Gibt Deckdaten weiter

Backend -> MariaDB: Speichert Deck ab


@enduml
</details>