package com.cdac.training.sms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.training.sms.exception.ResourceNotFoundException;
import com.cdac.training.sms.model.Student;
import com.cdac.training.sms.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentService sservice;

	@PostMapping("/students")
	public ResponseEntity<Student> saveStuDetails(@Validated @RequestBody Student student) {

		try {
			Student s = sservice.saveStudent(student);
			return ResponseEntity.status(HttpStatus.CREATED).body(s);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudent() {

		try {
			List<Student> s = sservice.listAll();
			return ResponseEntity.ok(s);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/students/{id}")

	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long sId)
			throws ResourceNotFoundException {

		Student student = sservice.getSingleStudent(sId)
				.orElseThrow(() -> new ResourceNotFoundException("Student Not FOund for this id " + sId));

		return ResponseEntity.ok().body(student);
//		return ResponseEntity.ok(student);
	}

	// update
	@PutMapping("/students/{id}")

	public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long sId,
			@Validated @RequestBody Student s) throws ResourceNotFoundException {

		Student student = sservice.getSingleStudent(sId)
				.orElseThrow(() -> new ResourceNotFoundException("Student Not Found From This ID" + sId));

		student.setName(s.getName());
		student.setCourse(s.getCourse());
		student.setMarks(s.getMarks());

		final Student updatestudent = sservice.saveStudent(student);
		return ResponseEntity.ok().body(updatestudent);

	}
	// delete

	@DeleteMapping("/students/{id}")

	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable(value = "id") Long sId)
			throws ResourceNotFoundException {

		Student student = sservice.getSingleStudent(sId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not Found from this Id " + sId));
		sservice.deleteStudentById(sId);

		Map<String, Boolean> response = new HashMap<String, Boolean>();

		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	// search

	// Open PostMan,make a GET
	// Request-http://localhost:8081/pms/api/products/search?name=laptop

	@GetMapping("/students/search")

	public ResponseEntity<?> searchStudentByName(@RequestParam("name") String name) {

		try {
			List<Student> students = sservice.searchStudentByName(name);
			if (students.isEmpty()) {

				return new ResponseEntity<>("Student Data Not Found", HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(students, HttpStatus.OK);

		} catch (Exception e) {
			// database Error
			return new ResponseEntity<>("An Error Occured ", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/students/searchMarks")
	public ResponseEntity<?> searchByMarks(@RequestParam("marks") int marks) {

		try {
			List<Student> students = sservice.searchByStudentMarks(marks);

			if (students.isEmpty()) {
				return new ResponseEntity<>("Student Data Not Found", HttpStatus.NOT_FOUND);

			}
			return new ResponseEntity<>(students, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Database Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// for more than one data post

		/*
		 * @PostMapping("/student") public ResponseEntity<List<Student>>
		 * createStudents(@RequestBody List<Student> students) {
		 * 
		 * List<Student>createdStudents = studentService.createStudents(students);
		 * 
		 * return ResponseEntity.ok(createdStudents); }
		 * 
		 * 
		 * 
		 * /
		 */

	}

	@GetMapping("/count")
	ResponseEntity<Long> countStudent() {

		long countStudents = sservice.countStudents();

		return ResponseEntity.ok(countStudents);
	}

	@GetMapping("/students/searchByCourse")
	public ResponseEntity<?> searchCourse(@RequestParam("course")String course)
	{
		
	try {
			List<Student> courses=sservice.searchByCourses(course);
			
		
			if(courses.isEmpty()) {
				
				return new ResponseEntity<>("Course Details Not  Found",HttpStatus.NOT_FOUND);
			
			}
			return new ResponseEntity<>(courses,HttpStatus.OK);
		
	}catch (Exception e){
		return new ResponseEntity<>("database Error Occured",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	}

}
//============================================================================
/*
 * @GetMapping("/students/searchMarks") public ResponseEntity<?>
 * searchByMarks(@RequestParam("marks") int marks) {
 * 
 * try { List<Student> students = sservice.searchByStudentMarks(marks);
 * 
 * if (students.isEmpty()) { return new ResponseEntity<>("List Is Empty",
 * HttpStatus.NOT_FOUND); } else { // This block will handle returning students
 * data ResponseEntity<?> responseEntity = new ResponseEntity<>(students,
 * HttpStatus.OK);
 * 
 * 
 * // Additional logic to return a response indicating pass/fail if (marks >=
 * 70) { Map<String, Boolean> response = new HashMap<>(); response.put("Pass",
 * Boolean.TRUE); // return ResponseEntity.ok(response);
 * System.out.println(response+"this is response"); }
 * 
 * // If marks are less than 70, just return the students list as the response
 * return responseEntity; }
 * 
 * } catch (Exception e) { return new
 * ResponseEntity<>("Database Error Occurred",
 * HttpStatus.INTERNAL_SERVER_ERROR); } } }
 */