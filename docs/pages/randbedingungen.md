# Randbedingungen

## 1.1 Technologische Randbedingungen

Programmiersprache: Java 
Version: 21

Frameworks:

- Spring Boot 3.4.4
- Spring Boot Starter Web, Security, Actuator, Data JPA
- Thymeleaf für serverseitiges Rendering von Web-Views
- Hibernate ORM 6.5.2 für Persistenz


Build-System: Maven

Test-Frameworks: JUnit, Mockito

Security: Spring Security 6 

Templating: Thymeleaf + Spring Security



Persistence-Layer: JPA (Hibernate), MariaDB 

## 1.2 Laufzeitumgebung

Lokal: Java Runtime Environment: Java 21 

Zielplattform: Docker Container


## 1.3 Infrastruktur- und Datenbankabhängigkeiten

Datenbanken: MariaDB

Drittanbieter: [Gwent.one](https://api.gwent.one/)


## 1.4 Werkzeuge und Build-Tools

Build-Tool: Maven (inkl. Plugins: Spring Boot, Flyway, JaCoCo für Testabdeckung)

Qualitätssicherung: JaCoCo für Code Coverage Reports

Versionsverwaltung: GitHub

Dependency Management: Maven Central Repository

## 1.5 Sicherheitsanforderungen (technologisch)

Authentifizierung & Autorisierung: Basierend auf Spring Security 

View-Schutz: Thymeleaf und Spring Security

