# Einführung

In dem Spiel "The Witcher 3: Wild Hunt" gibt es ein Minigame namens Gwent. Dieses Spiel ist ein Kartenspiel, bei dem zwei 
Spieler mit ihren Decks gegeneinander antreten und versuchen in zwei von drei Runden mehr Punkte als ihr Gegner zu machen.

Jedes Deck besteht aus mindestens 25 Karten und einem Anführer. Dieser Anführer gehört zu einer Fraktion und bestimmt somit 
welche fraktionsspezifischen Karten in das Deck eingebaut werden können. 
Von den Karten eines Decks müssen mindestens 13 Karten Einheiten sein. Zusätzlich hat jede Karte Rekrutierungskosten (bzw. 
Provisionskosten). Jeder Anführer hat einen Provisionsbonus. Der Provisionsbonus plus 150 ergibt die maximale Summe der
Rekrutierungskosten der Karten eines Decks.

Das Spiel läuft so ab, dass beide Spieler zu Beginn des Spiels 10 Karten aus ihrem Deck ziehen. Dann können bis zu zwei 
Karten abgelegt werden und neue Karten gezogen werden. Weitere Karten werden im Rest des Spiels nur noch gezogen, wenn
Karten- oder Anführereffekte dies verursachen. Anschließend beginnt das Spiel. 

Das Spiel dauert maximal 3 Runden. In jeder Runde legen die beiden Spieler abwechselnd ihre Karten, bis einer der Spieler passt.
Anschließend kann der Spieler, der noch nicht gepasst hat so lange weitere Karten ausspielen wie er will, und anschließend ebenfalls passen.
Nachdem beide Spieler gepasst haben endet die Runde und der Spieler mit den meisten Punkten hat die Runde gewonnen. 
Nach der Runde werden alle ausgespielten Karten in den Friedhof ihrer Spieler gelegt und die Punkte auf null zurückgesetzt.

Der erste Spieler der zwei Runden gewonnen hat, hat das Spiel gewonnen.

## Ziel des Projekts

Das Endziel dieses Projekts ist es den Deckbau und das Kartenziehen zu simulieren. Hierbei sollen das ziehen der initialen Hand, das
nachziehen durch den, zu Beginn des Spiels möglichen, Kartentausch und das ziehen von Karten durch Karten- und Anführereffekte simuliert werden.

Zusätzlich soll ein Benutzer der App die Möglichkeit haben die Hand zu bewerten und neu zu ziehen, um die neue Hand zu bewerten.
Ab einer bestimmten Menge an Bewertungen soll ein Score für das Deck gebildet werden, der den Vergleich verschiedener Decks 
durch den Benutzer ermöglicht.

## Qualitätsziele des Projekts

| Qualität        | Ziel                     |
|-----------------|--------------------------|
| Usability       | Intuitive, einfache Nutzung durch Benutzer |
| Wartbarkeit     | Klare Strukturierung des Codes |
| Portabilität    | Bereitstellung über Docker |
| Erweiterbarkeit | Einfache Integration neuer Features |

## Stakeholder des Projekts

| Rolle           | Interesse                                                    |
|-----------------|--------------------------------------------------------------|
| Endnutzer       | Einfache Deckerstellung und Speicherung und Testen des Decks |
| Entwickler      | Erweiterbarkeit, einfacher/gut strukturierter Code           |
| Administratoren | Einfaches Deployment per Docker                              |