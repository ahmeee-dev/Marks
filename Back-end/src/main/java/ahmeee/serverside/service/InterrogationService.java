package ahmeee.serverside.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ahmeee.serverside.repository.UserRepo;

@Service
public class InterrogationService {
	
	@Autowired
	UserRepo userRepo;

	// so, it should tale the request body, create the query and send it to the LLM
	// take the JSON format answer and extract the data from inside to either save it or calculate it

	//Interrogation request body:
	//	Argument,
	//	Synthesis, 
	//	Ignored piece from last call,
	//	Marks sum,
	//	request quantity

	// for the positive feedback given above 8, I can insert a pool of hundreds of ways to say a compliment and give it sometimes.

	// QUERIES
	/*
	 *		BOZZA
	 * {
	 * 	language: italian
	 * 	expected output: {
	 * 		"cutoff": porzione di contenuto che andrà integrata alla richiesta successiva poiché non completa in questa,
	 * 		"grade": voto da 1 a 10 dato alla validità sulla base della difficoltà inserita,
	 * 		"annotation": - questa parte va inserita solo se il voto <= 6.5 e c'è qualcosa di sbagliato Devi dire cosa c'è di sbagliato, o come si potrebbe dire meglio, nel primo caso, sii estremamente sintetico, 1-7 parole *aggiungi il fatto che a seconda della difficoltà vuoi l'annotazione anche sul lessico, e che per le difficoltà più basse vuoi invece più transigenza*,
	 * 		"synthesis": una sintesi che sia la sintesi precedente mischiato a quello che ha detto in questo contenuto, proporzionato,
	 * 	},
	 * 	context: Stai per ricevere il contenuto di un'interrogazione, il suo titolo, la sua sintesi e un pacchetto di altre informazioni, dovrai analizzare tutto e restituirmi quanto detto nell'expected output nel formato JSON, senza alcun testo aggiunto.
	 * 	answer format: JSON,
	 * 	argument: ...
	 * 	argument_context: l'argomento dell'interrogazione
	 * 	synthesis: ...
	 * 	synthesis_context: sintesi di quanto detto fino ad ora, funge da introduzione e contestualizzazione
	 * 	trimmed: parte che tagliata fuori dall'ultima richiesta, va posizionata prima del contenuto, così da collegare i pezzi
	 * 	content: il contenuto effettivo della porzione di interrogazione
	 * }
	 * 
	 *  	JSON
	  	{
			"language": "italian",
			"task": "Analizza la porzione di contenuto fornita come parte di un'interrogazione e restituisci un feedback strutturato nel formato specificato. La tua risposta deve essere esclusivamente in formato JSON valido, senza testo aggiuntivo o commenti.",
			"context": "Riceverai le seguenti informazioni: titolo dell'interrogazione, argomento, sintesi generale del discorso fino a questo punto, eventuale porzione tagliata ('trimmed') dalla richiesta precedente, e il contenuto effettivo di questa parte. Devi valutare la porzione attuale in base alla sua completezza, coerenza con la difficoltà prevista, correttezza linguistica e contenutistica. Se il livello di difficoltà è basso, sii più tollerante con la forma linguistica; se il livello è alto, presta maggiore attenzione al lessico e alla precisione terminologica.",
			"expected_output": {
				"cutoff": "Porzione di testo che appare interrotta o incompleta e dovrà essere integrata con la prossima richiesta. Se non vi sono elementi troncati, lascia stringa vuota.",
				"grade": "Voto da 1 a 10 sulla validità complessiva della risposta, basato su correttezza, coerenza e livello di difficoltà.",
				"wrong_part": "Inserisci solo se il voto è inferiore a 7. Indica in modo estremamente sintetico (1–7 parole) cosa è errato o come migliorare. Concentrati su imprecisioni concettuali o lessicali, a seconda della difficoltà.",
				"synthesis": "Sintesi proporzionata che integri la sintesi precedente con il nuovo contenuto, in forma fluida e coerente. Deve servire come base per la prossima estensione."
			},
			"answer_format": "JSON",
			"inputs": {
				"argument": "Argomento specifico dell'interrogazione.",
				"argument_context": "Breve descrizione contestuale dell'argomento trattato.",
				"synthesis": "Sintesi cumulativa del contenuto trattato fino a ora.",
				"synthesis_context": "Funzione di introduzione e raccordo per la parte attuale.",
				"trimmed": "Porzione troncata dall'input precedente che va anteposta al contenuto corrente.",
				"content": "Porzione di interrogazione da analizzare."
			}
		}

			PLAIN TEXT
		Sei un modello linguistico che analizza porzioni di interrogazioni orali di studenti in lingua italiana.
		Riceverai una serie di informazioni che descrivono il contesto dell’interrogazione e la porzione attuale da valutare.
		Il tuo compito è analizzare il contenuto ricevuto e restituire **esclusivamente** un oggetto JSON conforme alla seguente struttura:

		{
		"cutoff": "Porzione di contenuto che appare interrotta o incompleta e dovrà essere integrata nella prossima richiesta. Se non c’è alcuna interruzione, lascia stringa vuota.",
		"grade": "Voto numerico da 1 a 10 che valuta la validità complessiva della risposta in relazione alla difficoltà prevista.",
		"wrong_part": "Campo opzionale. Inseriscilo solo se il voto è minore di 7. Scrivi in massimo 7 parole cosa è errato o come si potrebbe migliorare, privilegiando la sintesi. Se la difficoltà è alta, valuta anche la precisione lessicale; se è bassa, sii più tollerante.",
		"synthesis": "Sintesi proporzionata e fluida che integri la sintesi precedente con il nuovo contenuto analizzato, mantenendo coerenza e continuità."
		}

		Criteri di valutazione

		* Analizza la correttezza concettuale, la coerenza con l’argomento e la completezza della parte.
		* Valuta il lessico in base alla difficoltà:

		* per difficoltà basse → più tolleranza lessicale;
		* per difficoltà alte → maggior rigore linguistico e terminologico.
		* Il voto non deve dipendere dalla lunghezza del testo, ma dalla qualità e coerenza.

		Input forniti

		Ti verranno passati i seguenti campi:

		
		{
		"argument": "Argomento specifico dell’interrogazione.",
		"argument_context": "Descrizione contestuale dell’argomento trattato.",
		"synthesis": "Sintesi cumulativa del contenuto trattato fino a ora.",
		"synthesis_context": "Funzione introduttiva o di raccordo rispetto alla parte attuale.",
		"trimmed": "Porzione troncata dall’input precedente da anteporre al contenuto corrente.",
		"content": "Porzione effettiva di interrogazione da analizzare."
		}
		

		Regole d’uscita

		* Restituisci solo il JSON, senza testo aggiuntivo, spiegazioni o commenti.
		* Il JSON deve essere ben formato e valido.


	 */

}
