# Randbedingungen

## 1.1 Technologische Randbedingungen

Programmiersprache: Java 
Version: 21

Frameworks:
- Spring Boot 3.4.4
- Spring Boot Starter Web, Security, Actuator, Data JPA
- Thymeleaf für serverseitiges Rendering von Web-Views
- Hibernate ORM 6.5.2 für Persistenz

  <!--      Flyway 11.6.0 für Datenbankmigrationen -->

Build-System: Maven

Test-Frameworks: JUnit (über Spring Boot Starter Test), Mockito

Security: Spring Security 6 + JWT (io.jsonwebtoken)

Templating: Thymeleaf + Spring Security Integration (thymeleaf-extras-springsecurity6)

<!--    Datenformate: JSON (über Gson)-->

Persistence-Layer: JPA (Hibernate), MariaDB für produktive Nutzung

## 1.2 Laufzeitumgebung

Java Runtime Environment (JRE): Java 21 erforderlich

Zielplattform: JVM-kompatible Umgebung (lokal, Server, ggf. Container)


## 1.3 Infrastruktur- und Datenbankabhängigkeiten

Datenbanken:

Produktiv: MariaDB (Zugriff über MariaDB JDBC-Treiber)

Test/Entwicklung: MariaDB (Zugriff über MariaDB JDBC-Treiber)



## 1.4 Werkzeuge und Build-Tools

Build-Tool: Maven (inkl. Plugins: Spring Boot, Flyway, JaCoCo für Testabdeckung)

Qualitätssicherung: JaCoCo für Code Coverage Reports

Versionsverwaltung: GitHub

Dependency Management: Maven Central Repository

## 1.5 Sicherheitsanforderungen (technologisch)

Authentifizierung & Autorisierung: Basierend auf Spring Security mit JWT (JSON Web Tokens)

View-Schutz: Integriert über Thymeleaf-Spring-Security-Extras

