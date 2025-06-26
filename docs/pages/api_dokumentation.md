# API-Dokumentation

Hier werden die API-Endpunkte der GwentApp beschrieben.

## Benutzerverwaltung

### 1. **http://localhost:8080/auth/register**

Der Endpunkt, der die Registrierung eines neuen Benutzers ermöglicht.

1. GET: erhält die Oberfläche zur Registrierung des neuen Benutzers
2. POST: Fügt einen neuen Benutzer in die Datenbank ein

   Parameter:

   - `user` (Gwentuser, erforderlich): Der Benutzer als Objekt Gwentuser mit den Parametern `username` und `password`

   Antwort:

   - Status 302 (IsFound): Der Benutzer wurde angelegt und der Browser leitet auf /auth/login weiter
   - Status 200 (IsOk): Der Benutzer existiert schon und wurde deshalb nicht angelegt

### 2. **http://localhost:8080/auth/login**

1. GET: erhält die Oberfläche zum Anmelden eines bereits existierenden Benutzers
2. POST: Meldet den Benutzer an
   
   Parameter:

   - `user` (Gwentuser, erforderlich): Der Benutzer als Objekt Gwentuser mit den Parametern `username` und `password`

   Antwort:

   - Status 302 (IsFound): Der Benutzer wurde angemeldet und der Browser leitet auf /buildDeck weiter
   - Status 200 (IsOk): Der Benutzer existiert noch nicht, oder das falsche Passwort wurde angegeben 



### 3. **http://localhost:8080/auth/logout**

   Ein standardmäßig von Spring Security implementierter Endpunkt, mit dem ein eingeloggter Benutzer sich abmelden kann.
   Nach der erfolgreichen Abmeldung wird auf /buildDeck weitergeleitet.


## Deck bauen

### 1. **GET http://localhost:8080/buildDeck**

   Dieser Endpunkt ermöglicht das zusammenstellen eines Decks. Hierbei gibt es zwei Modi:
   1. Öffentlich: Es ist kein Benutzer angemeldet. Das Deck kann nicht abgespeichert werden.
   2. Privat: Ein Benutzer hat sich angemeldet. Das Deck kann gespeichert werden.


### 2. **GET http://localhost:8080/buildDeck2**

   Gibt ein Byte-Array zurück, dass ein Bild ist.
   Parameter: 

- id: Die ID einer Karte
   

### 3. **POST http://localhost:8080/submit-deck**

   Speichert ein Deck ab.
   Parameter: 

   - `deck`: Ein String der aus einer Liste an IDs zusammengefügt wird
   - `leaderId`: (optional) Die ID des Anführers des Decks 

   Antwort:

   - Status 200 (IsOk): Das Deck wurde gespeichert
   
   Dies funktioniert nur, wenn der Benutzer angemeldet ist 