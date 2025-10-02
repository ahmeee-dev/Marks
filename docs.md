# Marks – Technical README

*L'applicazione è in fase attiva di sviluppo. Questo Readme si basa sulla versione finale desiderata.*

---

## Overview

**Marks** è un’applicazione mobile sviluppata con **React Native** (Frontend) e **Spring Boot** (Backend), progettata per aiutare gli studenti a prepararsi a interrogazioni simulate con l'ausilio di un tutor virtuale.

L’infrastruttura è containerizzata tramite **Docker** e destinata al deploy su **AWS**. Il sistema utilizza **PostgreSQL** come database centrale e **Redis** per la gestione della cache.

---

## Architettura

| Componente | Tecnologia | Ruolo |
| :--- | :--- | :--- |
| **Backend** | Java 17+, Spring Boot | Logica di business, API REST, integrazione Gemini. |
| **Frontend** | React Native con Expo | Interfaccia utente mobile (iOS/Android). |
| **Database** | PostgreSQL | Persistenza dati centralizzata. |
| **Cache** | Redis | Caching per alleggerire le query al DB. |
| **Infrastruttura** | Docker, AWS (ECS/EKS, RDS, Elasticache) | Containerizzazione e orchestrazione cloud. |
| **API esterne** | Gemini API | Elaborazione del linguaggio naturale e simulazione d'interrogazione. |

---

## Prerequisiti

Per lo sviluppo e l'esecuzione in locale sono necessari:

* **Java 17** o superiore
* **Node.js** + `npm`/`yarn`
* **Docker** + **Docker Compose**
* **PostgreSQL** (locale o remoto)
* **Redis** (locale o remoto)
* Connessione Internet stabile
* Chiave API valida per **Gemini**

---

## Installazione e Setup

### 1. Clonazione della Repository

```bash
git clone [https://github.com/ahmeee-dev/marks.git](https://github.com/ahmeee-dev/marks.git)
cd marks
````

### 2\. Backend (Spring Boot)

#### Configurazione

Spostarsi nella cartella `backend` e creare il file di configurazione `application.properties`:

```bash
cd backend
```

**`application.properties`**

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/marks
spring.datasource.username=<db_user>
spring.datasource.password=<db_password>
spring.jpa.hibernate.ddl-auto=update

redis.host=localhost
redis.port=6379

gemini.api.key=<chiave_api_gemini>
```

#### Avvio

Scegliere tra esecuzione diretta o Docker:

**Esecuzione diretta (richiede Java e Maven):**

```bash
./mvnw spring-boot:run
```

**Esecuzione con Docker Compose:**

```bash
docker-compose up backend
```

### 3\. Frontend (React Native + Expo)

#### Installazione Dipendenze

Spostarsi nella cartella `frontend` e installare le dipendenze Node:

```bash
cd ../frontend
npm install
```

#### Avvio

Avviare l’app in modalità sviluppo con Expo:

```bash
npx expo start
```

Seguire le istruzioni nel terminale per eseguire l'app su un emulatore o dispositivo mobile (scannerizzando il QR code).

### 4\. Database (PostgreSQL)

#### Setup Manuale

Creare un'istanza PostgreSQL e il database necessario:

```sql
CREATE DATABASE marks;
```

#### Setup con Docker Compose

Se si utilizza Docker Compose per l'infrastruttura locale:

```bash
docker-compose up postgres
```

### 5\. Cache (Redis)

Eseguire Redis in locale o tramite Docker:

```bash
docker run --name redis -p 6379:6379 -d redis
```

-----

## Deploy su AWS

Il deploy in ambiente di produzione avverrà su AWS con la seguente configurazione:

1.  Creare un cluster su **ECS/EKS** per l'orchestrazione dei container.
2.  Configurare i servizi tramite Docker Compose o **Terraform**.
3.  Collegare l'applicazione a un'istanza gestita di **PostgreSQL (RDS)** e **Redis (Elasticache)**.
4.  Definire **variabili d’ambiente sicure** per credenziali e chiavi API (es. tramite AWS Secrets Manager).

-----

## Testing

I test verranno eseguiti in locale simulando sessioni con dispositivi (desktop + mobile).

È possibile lanciare i test unitari e di integrazione del Backend con:

```bash
./mvnw test
```

-----

## Contributi

Siamo aperti a suggerimenti e proposte.

1.  **Forkare** il progetto.
2.  Creare un branch dedicato: `git checkout -b feature/nome-feature`
3.  Aprire una **Pull Request** descrivendo chiaramente le modifiche apportate e i problemi risolti.

-----

## Licenza

**Uso privato consentito.** Non è permessa la rivendita o lo sfruttamento commerciale senza autorizzazione.