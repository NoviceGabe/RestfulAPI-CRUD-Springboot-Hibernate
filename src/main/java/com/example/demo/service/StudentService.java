package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Student;

public interface StudentService {
	void createStudent(Student student);
	void createStudents(List<Student> students);
	Optional<Student> findStudent(int id);
	List<Student> getStudents();
	void updateStudent(Student student);
	void updateStudents(List<Student> students);
	void deleteStudent(int id);
	void deleteStudents(List<Integer> ids);
}