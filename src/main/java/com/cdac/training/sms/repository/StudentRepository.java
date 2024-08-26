package com.cdac.training.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.training.sms.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT s FROM Student s WHERE s.name = :name")
	
	//@Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Student> findByNameIgnoreCase(@Param("name") String name);

//	@Query("SELECT s FROM Student s WHERE s.id = :id")
//	Student findById(@Param("id") Long id);

	
	@Query("select s from Student s where s.marks>=:marks")
	List<Student> findByMArks(@Param("marks")int marks);

	//count
	@Query ("select count(s) from Student s")
	long countByStudent();


  @Query("select s from Student s where s.course=:course")
  List<Student>SearchByCourse(@Param("course")String course);
  

}
