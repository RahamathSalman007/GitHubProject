package com.ems.practice.bean;

public class Course {
	
	private int courseId;
	private String courseName;
	private String courseDuration;
	private double courseFee;
	
	public Course(){}

	public Course(int courseId, String courseName, String courseDuration, double courseFee) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseFee = courseFee;
	}

	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public double getCourseFee() {
		return courseFee;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public void setCourseFee(double courseFee) {
		this.courseFee = courseFee;
	}
	
	@Override
	public String toString(){
		return courseId + "\t" + courseName  + "\t" + courseDuration  + "\t" + courseFee;
	}
	

}
