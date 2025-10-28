package ahmeee.serverside.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FirstQuestionResponse {
	
	final private String type = "first_question";
	@JsonProperty("new_question")
	private String newQuestion;
	@JsonProperty("new_question_synthesis")
	private String newQuestionSyntheis;

	public FirstQuestionResponse() {}

	public String getType() {
		return type;
	}
	public String getNewQuestion() {
		return newQuestion;
	}
	public void setNewQuestion(String newQuestion) {
		this.newQuestion = newQuestion;
	}
	public String getNewQuestionSyntheis() {
		return newQuestionSyntheis;
	}
	public void setNewQuestionSyntheis(String newQuestionSyntheis) {
		this.newQuestionSyntheis = newQuestionSyntheis;
	}

	
}
