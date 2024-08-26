package com.cdac.training.sms.model;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Student {
	
	
	@Id
	@SequenceGenerator(name="Roll_No", initialValue = 100, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.IDENTITY ,generator="Roll_No")
	private Long id;
	private String name;
	private String course ;
	private int marks;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(Long id, String name, String course, int marks) {
		super();
		this.id = id;
		this.name = name;
		this.course = course;
		this.marks = marks;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	
	

}
