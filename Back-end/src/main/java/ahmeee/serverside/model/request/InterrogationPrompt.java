package ahmeee.serverside.model.request;

public class InterrogationPrompt {

    private String language;
    private String task;
    private String context;
    private String expectedOutput;
    private String inputStructure; // opzionale: per descrivere struttura JSON input
    private String outputRules;    // opzionale: regole di formattazione output
	private InterrogationRequest createInterrogationRequest;

    public InterrogationRequest getCreateInterrogationRequest() {
		return createInterrogationRequest;
	}

	public void setCreateInterrogationRequest(InterrogationRequest createInterrogationRequest) {
		this.createInterrogationRequest = createInterrogationRequest;
	}

	public InterrogationPrompt() {}

    public InterrogationPrompt(String language, String task, String context, String expectedOutput,
                         String inputStructure, String outputRules, InterrogationRequest createInterrogationRequest) {
        this.language = language;
        this.task = task;
        this.context = context;
        this.expectedOutput = expectedOutput;
        this.inputStructure = inputStructure;
        this.outputRules = outputRules;
        this.createInterrogationRequest = createInterrogationRequest;
    }

    // getter e setter
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }

    public String getContext() { return context; }
    public void setContext(String context) { this.context = context; }

    public String getExpectedOutput() { return expectedOutput; }
    public void setExpectedOutput(String expectedOutput) { this.expectedOutput = expectedOutput; }

    public String getInputStructure() { return inputStructure; }
    public void setInputStructure(String inputStructure) { this.inputStructure = inputStructure; }

    public String getOutputRules() { return outputRules; }
    public void setOutputRules(String outputRules) { this.outputRules = outputRules; }

    public static InterrogationPrompt createInterrogationPrompt(InterrogationRequest createInterrogationRequest) {
        String language = "italian";
        String task = "Analizza porzioni di interrogazioni orali e valuta la qualità.";
        String context = """
Sei un modello linguistico che analizza porzioni di interrogazioni orali di studenti in lingua italiana.
Riceverai informazioni che descrivono il contesto dell’interrogazione e la porzione attuale da valutare.
Il tuo compito è analizzare il contenuto ricevuto e restituire **esclusivamente** un oggetto JSON conforme alla struttura specificata.
""";
        String expectedOutput = """
{
    "cutoff": "Porzione di contenuto interrotta o incompleta da integrare nella prossima richiesta. Se non c’è alcuna interruzione, lascia stringa vuota.",
    "grade": "Voto numerico da 4 a 10 che valuta la validità complessiva della risposta in relazione alla difficoltà prevista.",
    "wrong_part": "Campo opzionale. Inseriscilo solo se il voto è minore di 7. Sintetizza in massimo 7 parole cosa è errato o come migliorare la risposta. Per difficoltà alte valuta anche la precisione terminologica; per difficoltà basse sii più tollerante.",
    "synthesis": "Sintesi proporzionata e fluida che integri la sintesi precedente con il nuovo contenuto analizzato, mantenendo coerenza e continuità."
}
""";
        String inputStructure = """
{
    "text_attributes": {
        "argument": "Argomento specifico dell’interrogazione.",
        "synthesis": "Sintesi cumulativa del contenuto trattato fino a ora.",
        "trimmed": "Porzione troncata dall’input precedente da integrare con il contenuto corrente.",
        "content": "Porzione effettiva di interrogazione da analizzare."
    },
    "grade_attributes": {
        "grade": voto totalizzato fino ad ora,
        "quantity": quantità di voti
    },
    "settings": {
        "difficulty": difficoltà selezionata (1-4, dove 1 → aspettativa 6-7, 2 → 7-8, ecc.)
    }
}
""";
        String outputRules = """
* Restituisci solo il JSON, senza testo aggiuntivo, spiegazioni o commenti.
* Il JSON deve essere ben formato e valido.
* Valuta correttezza concettuale, coerenza con l’argomento, completezza e qualità lessicale in base alla difficoltà.
""";

        return new InterrogationPrompt(language, task, context, expectedOutput, inputStructure, outputRules, createInterrogationRequest);
    }
}
