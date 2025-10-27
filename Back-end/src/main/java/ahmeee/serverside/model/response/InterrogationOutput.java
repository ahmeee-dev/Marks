package ahmeee.serverside.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class InterrogationOutput {

	@JsonProperty("new_content")
	private NewContent newContent;
	private Previous previous;

	public InterrogationOutput() {}

	public NewContent getNewContent() {
		return newContent;
	}

	public void setNewContent(NewContent newContent) {
		this.newContent = newContent;
	}

	public Previous getPrevious() {
		return previous;
	}

	public void setPrevious(Previous previous) {
		this.previous = previous;
	}

	public class NewContent {
		@JsonProperty("new_question")
		private String newQuestion;
		@JsonProperty("new_question_synthesis")
		private String newQuestionSynyhesis;

		public NewContent() {}

		public String getNewQuestion() {
			return newQuestion;
		}
		public void setNewQuestion(String newQuestion) {
			this.newQuestion = newQuestion;
		}
		public String getNewQuestionSynyhesis() {
			return newQuestionSynyhesis;
		}
		public void setNewQuestionSynyhesis(String newQuestionSynyhesis) {
			this.newQuestionSynyhesis = newQuestionSynyhesis;
		}
	}

	public class Previous {
		@Min(4)
		@Max(10)
		private int grade;
		private String annotation;
		@JsonProperty("exposition_annotation")
		private String expositionAnnotation;

		public Previous() {}

		public int getGrade() {
			return grade;
		}
		public void setGrade(int grade) {
			this.grade = grade;
		}
		public String getAnnotation() {
			return annotation;
		}
		public void setAnnotation(String annotation) {
			this.annotation = annotation;
		}
		public String getExpositionAnnotation() {
			return expositionAnnotation;
		}
		public void setExpositionAnnotation(String expositionAnnotation) {
			this.expositionAnnotation = expositionAnnotation;
		}
	}
}

// {
//     "new_content": {
//         "new_question": "nuova domanda basata su argument",
//         "new_question_synthesis": "sintesi di circa 4 parole"
//     },
//     "previous": {
//     "grade": numero_da_4_a_10,
//     "annotation": "errore concettuale in max 10 parole o stringa vuota",
//     "exposition_annotation": "correzione forma se attiva, altrimenti stringa vuota"
//     }
// }
