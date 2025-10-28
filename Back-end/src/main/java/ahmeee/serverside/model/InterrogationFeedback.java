package ahmeee.serverside.model;

public class InterrogationFeedback {
	
	int grade;
	String annotation;

	public InterrogationFeedback(String annotation, int grade) {
		this.annotation = annotation;
		this.grade = grade;
	}

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
}
