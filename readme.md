# Marks 

*L'applicazione è in fase attiva di sviluppo. Questo Readme si basa sulla versione finale, fatta eccezione per la sezione 'Roadmap'.*

---

## Descrizione

**Marks** è un’applicazione mobile pensata per **studenti** che vogliono prepararsi alle interrogazioni in modo efficace.

L’obiettivo è eliminare la sensazione di “ripetere al muro”, offrendo un **tutor virtuale** capace di restituire **feedback immediati**, suggerimenti mirati e una valutazione realistica della propria esposizione.

---

## Funzionalità principali

* **Interrogazione simulata con feedback immediato:** A seconda del grado di difficoltà scelto, lo studente riceve valutazioni e consigli a intervalli regolari.
* **Resoconto finale:** Al termine della sessione viene fornito un riepilogo con indicazioni su come migliorare l'esposizione e altre metriche utili.
* **Modalità domande da appunti:** Possibilità di caricare appunti o un argomento, generando domande a cui rispondere.
* **Difficoltà regolabile:** I diversi livelli si basano sul voto desiderato.
* **Modalità flash (in valutazione):** Domande rapide con risposte a scelta binaria, gestite tramite swipe per una preparazione immediata.

---

## Piattaforma

Marks è sviluppata come **applicazione mobile** e sarà disponibile su **App Store** e **Google Play**.

---

## Installazione

### Utenti finali
Scarica l'app da App Store o Google Play (presto disponibili).

### Sviluppatori
1.  Clonare la repository.
2.  Configurare i file `application.properties` con le proprie credenziali e database.
3.  Eseguire il deploy in locale tramite **Docker**.

---

## Requisiti

* Chiave **API Gemini** per l’elaborazione.
* **Java**, **Expo**, **Postgres** e **Docker** per il deploy in locale.
* Una connessione stabile a Internet.

---

## Architettura

* **Backend:** Java con Spring Boot.
* **Frontend:** React Native.
* **Database centrale:** PostgreSQL.
* **Cache:** Redis per alleggerire le query al database.
* **Infrastruttura:** Docker e AWS.

---

## Licenza

Il software è disponibile per **uso privato e personale** da parte degli sviluppatori.

**Non è consentita** la rivendita o lo sfruttamento commerciale senza autorizzazione.

---

## Crediti

Un ringraziamento speciale a **Telusko**, per i corsi introduttivi e di approfondimento su YouTube che hanno contribuito in maniera significativa alla crescita del progetto.

---

## Roadmap

Next steps di sviluppo:

1.  Implementazione di Redis per ridurre il carico dato al DB.
2.  Implementazione dell’infrastruttura Docker.
3.  Aggiunta delle funzionalità principali di simulazione e feedback.
4.  Sviluppo dell’interfaccia frontend.

---

## Contributi

Suggerimenti e proposte sono ben accetti! Chiunque voglia contribuire può proporre idee o aprire discussioni tramite **issue**.

*Io, sviluppatore dell'applicazione mi dichiaro contro ogni forma di oppressione e sterminio, senza giustificazione alcuna. Chiunque ritenga opinabile la possibilità alla vita e alla dignità è pregato di non utilizzare il Marks in quanto non ben gradito*