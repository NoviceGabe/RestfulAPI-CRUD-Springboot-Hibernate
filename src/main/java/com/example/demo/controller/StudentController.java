package com.example.demo.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;

	// fetches all resources

	@GetMapping("/student")
	public Collection<Student> getAllStudents() {
		return studentService.getStudents();
	}

	// fetches resource
	
	@GetMapping("/student/{id}")
	public Student getStudent(@PathVariable int id) {
		Optional<Student> student = studentService.findStudent(id);
		if (student.isPresent()) {
			return student.get();
		}

		return null;
	}

	// creates resource
	
	@PostMapping("/student/create")
	public String createStudent(@RequestBody Student student) {
		studentService.createStudent(student);
		return "Student " + student.getFirst_name() + " " + student.getLast_name() + " created";
	}
	
	@PostMapping("/student/create/multiple")
	public String createStudent(@RequestBody List<Student> students) {
		try {
			studentService.createStudents(students);
			return "Student(s) created"; 
		}catch(Exception e) {
			e.printStackTrace();
			return "No Student(s) not created"; 
		}
		
	}

	// updates resource

	@RequestMapping(value = "/student/update/{id}", method = RequestMethod.PUT)
	public String updateStudent(@RequestBody Student student, @PathVariable int id) {
		Optional<Student> result = studentService.findStudent(id);
		if (result.isPresent()) {
			studentService.updateStudent(student);
			return "Student " + student.getFirst_name() + " " + student.getLast_name() + " updated";
		}

		return "Student not updated";
	}
	

	// updates multiple resources
	
	@PutMapping("/student/update")
	public String updateStudents(@RequestBody List<Student> students) {
		
		try {
			studentService.updateStudents(students);
			return "Student(s) updated";
		}catch(Exception e) {
			e.printStackTrace();
			return  "No Student(s) not updated";
		}
	}

	// deletes resource

	@DeleteMapping("/student/delete/{id}")
	public String deleteStudent(@PathVariable int id) {
		Optional<Student> result = studentService.findStudent(id);
		if (result.isPresent()) {
			studentService.deleteStudent(id);
			Student student = result.get();
			return "Student " + student.getFirst_name() + " " + student.getLast_name() + " deleted";
		}

		return "Student not deleted";
	}

	// deletes multiple resources

	@DeleteMapping(value = "/student/delete", params = "ids")
	public String deleteStudents(@RequestParam  List<Integer> ids) {
		try {
			studentService.deleteStudents(ids);
			return "Student(s) deleted";
		}catch(Exception e) {
			e.printStackTrace();
			return "No Student(s) not deleted";
		}
	}
}
