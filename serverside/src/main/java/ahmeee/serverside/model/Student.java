package ahmeee.serverside.model;

public class Student {
	
	String name;
	int marks;

	public Student(String name, int marks) { this.name = name; this.marks = marks; }

	public String getName() {
		return name;
	}

	public int getMarks() {
		return marks;
	}

	@Override
	public String toString() {
		return "Student: name= " + name + " marks= " + marks;
	}
}
