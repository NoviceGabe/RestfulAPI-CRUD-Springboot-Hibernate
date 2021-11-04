package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public void createStudent(Student student) {
		studentRepo.save(student);
	}
	

	@Override
	public void createStudents(List<Student> students) {
		studentRepo.saveAll(students);
	}


	@Override
	public Optional<Student> findStudent(int id) {
		return studentRepo.findById(id);
	}

	@Override
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<>();
		studentRepo.findAll().forEach(students::add);
		return students;
	}

	@Override
	public void updateStudent(Student student) {
		createStudent(student);
				
	}
	

	@Override
	public void updateStudents(List<Student> students) {
		createStudents(students);
	}


	@Override
	public void deleteStudent(int id) {
		studentRepo.deleteById(id);
	}

	@Override
	public void deleteStudents(List<Integer> ids) {
		studentRepo.deleteAllById(ids);
		
	}


}
