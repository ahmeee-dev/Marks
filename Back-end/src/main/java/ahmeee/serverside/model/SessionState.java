package ahmeee.serverside.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class SessionState {
	
	int difficulty;
	boolean languageEvaluation;
	String argument;
	Map<String, Integer> previousQuestions = new HashMap<>();
	int grade;
	int chunksNumber;
	String answer;
	File fileObj;

	public SessionState() {}

	public void addQuestion(int newGrade, String question) {
		int questionQuantity = previousQuestions.size() + 1;
		this.grade = (this.grade + newGrade) / questionQuantity;
		previousQuestions.put(question, newGrade);
	}

	public int getChunksNumber() {
		return chunksNumber;
	}
	public void setChunksNumber(int chunksNumber) {
		this.chunksNumber = chunksNumber;
	}
	public void addChunksCount() {
		this.chunksNumber++;
	}

	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void addAnswer(String chunk) {
		this.answer += chunk;
	}

	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public boolean isLanguageEvaluation() {
		return languageEvaluation;
	}
	public void setLanguageEvaluation(boolean languageEvaluation) {
		this.languageEvaluation = languageEvaluation;
	}

	public String getArgument() {
		return argument;
	}
	public void setArgument(String argument) {
		this.argument = argument;
	}

	public Map<String, Integer> getPreviousQuestions() {
		return previousQuestions;
	}
	public void setPreviousQuestions(Map<String, Integer> previousQuestions) {
		this.previousQuestions = previousQuestions;
	}

	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

	public File getFileObj() {
		return fileObj;
	}
	public void setFileObj(File fileObj) {
		this.fileObj = fileObj;
	}

}
