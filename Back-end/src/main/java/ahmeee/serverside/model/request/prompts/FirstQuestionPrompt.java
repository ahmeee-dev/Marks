package ahmeee.serverside.model.request.prompts;

public class FirstQuestionPrompt {

	private String argument;
	private int difficulty;
	private String prompt;
	private String output;

	public FirstQuestionPrompt() {}

	public String getArgument() {
		return argument;
	}
	public void setArgument(String argument) {
		this.argument = argument;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public static FirstQuestionPrompt createFirstQuestionPrompt(String argument, int difficulty) {

		FirstQuestionPrompt firstQuestionPrompt = new FirstQuestionPrompt();
		firstQuestionPrompt.setArgument(argument);
		firstQuestionPrompt.setDifficulty(difficulty);

		firstQuestionPrompt.setPrompt("""
Agisci come un assistente intelligente per interrogazioni orali automatizzate.
Il tuo compito è:

1. Generare una domanda coerente con la difficoltà scelta e con l'argomento selezionato.
2. Restituire un output JSON con la struttura specificata sotto.
3 Includi una sintesi di circa 4 parole nel campo `"new_question_synthesis"`.

Regole:
* Non spiegare le scelte effettuate.
* Non aggiungere testo o commenti fuori dal JSON.
* Mantieni un linguaggio naturale, preciso e didattico.
* Rispetta rigorosamente la struttura JSON indicata.

Formato dell’output:
				""");
		firstQuestionPrompt.setOutput("""
{
    "new_question": "nuova domanda basata su argument",
    "new_question_synthesis": "sintesi di circa 4 parole"
}
		""");

		return firstQuestionPrompt;
	}
}
