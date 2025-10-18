package ahmeee.serverside.model.response;

public class InterrogationResponse {

	String cutoff;
	Integer grade;
	String annotations;
	String synthesis;

    public InterrogationResponse() {}
	
	public String getCutoff() {
		return cutoff;
	}
	public void setCutoff(String cutoff) {
		this.cutoff = cutoff;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getAnnotations() {
		return annotations;
	}
	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}
	public String getSynthesis() {
		return synthesis;
	}
	public void setSynthesis(String synthesis) {
		this.synthesis = synthesis;
	}
}
