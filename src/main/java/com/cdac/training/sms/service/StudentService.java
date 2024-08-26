package com.cdac.training.sms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.cdac.training.sms.model.Student;
import com.cdac.training.sms.repository.StudentRepository;

@Service
public class StudentService {
	
	
	@Autowired
	private StudentRepository srepo;
	
	
	public Student saveStudent(Student s) {
		
		return srepo.save(s);
	}
	
	public List<Student> listAll(){
		
		return srepo.findAll();
	}
	
public Optional<Student> getSingleStudent(long id){
	
	return srepo.findById(id);
}
public void deleteStudentById(long id) {
	
	srepo.deleteById(null);
	
}
public List<Student> searchStudentByName(String name){
	
	return srepo.findByNameIgnoreCase(name);
}

public List<Student> searchByStudentMarks(int marks){
	
	return srepo.findByMArks(marks);
}

//count 
public long countStudents() {
	
	return srepo.countByStudent();
}

public List<Student> searchByCourses(String course){
	
	return srepo.SearchByCourse(course);
}

}
//for more than one data post through postman it is just like save one by one data but in this we can post multiple data

/*
 * public List<Student> createStudents(List<Student> students) {
	        // Business logic to process the list of students
	        // For example, save them to the database
	        for (Student student : students) {
	            // Save each student (pseudo-code)
	            // studentRepository.save(student);
	            System.out.println("Saving student: " + student.getStudentName());
	        }
	        return students;
	    }
 * 
 * /
 */



