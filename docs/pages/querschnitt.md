# Querschnittskonzepte

## Sicherheit

Zur Sicherung der Endpunkte wurde Spring Security eingebaut und konfiguriert

## User Interface

Die Benutzeroberflächen wurden mit einer Kombination aus den folgenden Technologien erstellt:
- Thymeleaf
- .html Code
- .js Code
- CSS Code
- Bootstrap

HTML-Code, CSS-Code und Bootstrap sind für das Aussehen der Oberflächen verantwortlich.

Thymeleaf und HTML-Code sind für das Einbauen von Abfragen und das Erhalten von Daten aus dem Backend verantwortlich.

Java-Script-Code ist für die funktionalität in der Oberfläche selbst und das Ausführen von POST-Requests zuständig. 


## Qualitätssichernde Maßnahmen und Tests

- Unit-Tests:  JUnit5 und Mockito

- Integration-Tests: SpringBootTest und MockMvc

- e2e-Tests: Playwright

- Penetrations-Tests: mit spring-security-test 

- Codeanalyse: Testabdeckung mit JaCoCo