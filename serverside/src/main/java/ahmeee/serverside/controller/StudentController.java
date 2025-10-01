package ahmeee.serverside.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ahmeee.serverside.model.Student;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {
	
	private final List<Student> students = new ArrayList<>(List.of(
		new Student("Marcio", 29),
		new Student("Luca", 23),
		new Student("Diego", 11)
	));

	@GetMapping("/students")
	public List<Student> getStudents() {
		return students;
	}

	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student) {
		students.add(student);
		return student;
	}

	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	

}
