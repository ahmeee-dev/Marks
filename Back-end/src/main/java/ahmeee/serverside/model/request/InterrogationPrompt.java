package ahmeee.serverside.model.request;

import java.util.ArrayList;

  //set json representations
public class InterrogationPrompt {
    private String prompt;
    private InterrogationInput interrogationInput;
    private String output;
    
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public InterrogationInput getInput() {
        return interrogationInput;
    }

    public void setInput(InterrogationInput interrogationInput) {
        this.interrogationInput = interrogationInput;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public InterrogationPrompt() {}

 
        public static InterrogationPrompt createInterrogationPrompt(InterrogationInput interrogationInput) {
            String prompt = """
Agisci come un assistente intelligente per interrogazioni orali automatizzate.
Il tuo compito è:

1. Analizzare la risposta precedente in base all’argomento e alle impostazioni.
2. Generare una nuova domanda coerente con la difficoltà scelta, diversa da tutte quelle presenti in `previous_list`.
3. Restituire un output JSON con la struttura specificata sotto.

Valutazione della risposta precedente (`previous`):

* Assegna un voto (`grade`) compreso tra 4 e 10.

  * Una risposta corretta al 100% corrisponde a 9.
  * Il 10 si assegna solo se la risposta è eccellente per completezza, chiarezza e approfondimento.
* La valutazione deve essere coerente con la difficoltà (`settings.difficulty`):

  * difficoltà 1 → aspettativa di voto 6–7
  * difficoltà 2 → aspettativa di voto 7–8
  * difficoltà 3 → aspettativa di voto 8–9
  * difficoltà 4 → aspettativa di voto 9–10
* Se la risposta contiene errori concettuali, inserisci un riassunto dell’errore (massimo 10 parole) nel campo `"annotation"`.
  Se non vi sono errori, lascia `"annotation"` vuoto.
* Se `settings.exposition_judgement` è `true`, valuta anche la chiarezza espositiva:

  * Se carente, scrivi la forma corretta o migliorata in `"exposition_annotation"`.
  * Se adeguata o disattivata, lascia `"exposition_annotation"` vuoto.

Generazione della nuova domanda (`new`):

* Crea una nuova domanda attinente allo stesso `argument`.
* La difficoltà della domanda deve rispecchiare `settings.difficulty`, con un incremento graduale se le risposte precedenti sono state buone.
* La nuova domanda deve essere esplicitamente diversa da tutte le domande presenti in `previous_list`, anche nella sintesi.
* Includi una sintesi di circa 4 parole nel campo `"new_question_synthesis"`.

Regole:
* Non spiegare le scelte effettuate.
* Non aggiungere testo o commenti fuori dal JSON.
* Mantieni un linguaggio naturale, preciso e didattico.
* Rispetta rigorosamente la struttura JSON indicata.

Formato dell’output:
L’output deve essere un JSON valido, senza testo aggiuntivo, con questa struttura esatta:

                    """;

            String output = """
{
    "new": {
        "new_question": "nuova domanda basata su argument",
        "new_question_synthesis": "sintesi di circa 4 parole"
    },
    "previous": {
    "grade": numero_da_4_a_10,
    "annotation": "errore concettuale in max 10 parole o stringa vuota",
    "exposition_annotation": "correzione forma se attiva, altrimenti stringa vuota"
    }
}
            """;
            InterrogationPrompt result = new InterrogationPrompt();
            result.setInput(interrogationInput);
            result.setPrompt(prompt);
            result.setOutput(output);
            return result;
        }

}


