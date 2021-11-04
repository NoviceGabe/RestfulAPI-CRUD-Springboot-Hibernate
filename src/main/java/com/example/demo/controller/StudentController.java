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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;

	// fetches all resources

	@GetMapping("/student")
	public  @ResponseBody Collection<Student> getAllStudents() {
		return studentService.getStudents();
	}

	// fetches resource
	
	@GetMapping("/student/{id}")
	public  @ResponseBody Student getStudent(@PathVariable int id) {
		Optional<Student> student = studentService.findStudent(id);
		if (student.isPresent()) {
			return student.get();
		}

		return null;
	}

	// creates resource
	
	@PostMapping("/student/create")
	public @ResponseBody String createStudent(@RequestBody Student student) {
		studentService.createStudent(student);
		return "Student " + student.getFirstName() + " " + student.getLastName() + " created";
	}
	
	@PostMapping("/student/create/multiple")
	public  @ResponseBody String createStudent(@RequestBody List<Student> students) {
		try {
			studentService.createStudents(students);
			return "Student(s) created"; 
		}catch(Exception e) {
			e.printStackTrace();
			return "No Student(s) created"; 
		}
		
	}

	// updates resource

	@PutMapping("/student/update/{id}")
	public  @ResponseBody String updateStudent(@RequestBody Student student, @PathVariable int id) {
		Optional<Student> result = studentService.findStudent(id);
		if (result.isPresent()) {
			studentService.updateStudent(student);
			return "Student " + student.getFirstName() + " " + student.getLastName() + " updated";
		}

		return "Student not updated";
	}
	

	// updates multiple resources
	
	@PutMapping("/student/update")
	public  @ResponseBody String updateStudents(@RequestBody List<Student> students) {
		
		try {
			studentService.updateStudents(students);
			return "Student(s) updated";
		}catch(Exception e) {
			e.printStackTrace();
			return  "No Student(s) updated";
		}
	}

	// deletes resource

	@DeleteMapping("/student/delete/{id}")
	public @ResponseBody String deleteStudent(@PathVariable int id) {
		Optional<Student> result = studentService.findStudent(id);
		if (result.isPresent()) {
			studentService.deleteStudent(id);
			Student student = result.get();
			return "Student " + student.getFirstName() + " " + student.getLastName() + " deleted";
		}

		return "Student not deleted";
	}

	// deletes multiple resources

	@DeleteMapping(value = "/student/delete", params = "ids")
	public @ResponseBody String deleteStudents(@RequestParam  List<Integer> ids) {
		try {
			studentService.deleteStudents(ids);
			return "Student(s) deleted";
		}catch(Exception e) {
			e.printStackTrace();
			return "No Student(s) deleted";
		}
	}
}
