# Bausteinsicht

## Level 1 - Gesamtüberblick


![Baustein_ebene_1.png](bilder%2FBaustein_ebene_1.png)

[XML-Code](XML-Code%2FBaustein_ebene_1.drawio.xml)

Der Browser nimmt Requests des Benutzers entgegen und gibt diese Requests an den 
Backend-Server weiter. Der Backend-Server verarbeitet die Requests und sorgt für die 
Anzeige des Ergebnisses. Beim verarbeiten des Requests greift der 
Backend-Server auf die Daten der MariaDB zurück und holt sich eventuelle Bilder von der Externen
API.

## Level 3 - Detailansicht

![Baustein_ebene_3.png](bilder%2FBaustein_ebene_3.png)

[XML-Code](XML-Code%2FBaustein_ebene_3.drawio.xml)

Der Browser nimmt Requests des Benutzers an. Zur Authentifizierung werden die Endpunkte des 
UserControllers verwendet. Dieser holt sich die relevanten Benutzerdaten aus den entsprechenden
Tabellen der Datenbank. Zur sicherung der Endpunkte wird der UserController von Spring Security unterstützt.
Neben den Authentifizierungsendpunkten gibt es noch die Endpunkte des CardControllers. Diese beinhalten im moment 
alle anderen 
Endpunkte der Applikation. Die Kartendaten der Anzeige werden aus den entsprechenden Tabellen der Datenbank 
geholt, während die Bilder über den CardLoader von der externen API geholt werden.
Der Cardloader ist auch dafür verantwortlich, dass die Kartendaten von der externen API in die 
entsprechenden Tabellen der Datenbank übertragen werden.