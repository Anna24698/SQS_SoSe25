# Probleme

- Spring Boot beendet sich selber (mit code 1):
  
    Grund: Spring Boot startet bevor MariaDB fertig hochgefahren ist
    Lösungsvorschläge: den gwentapplication-container manuell neustarten, oder die container mit docker compose up neustarten  