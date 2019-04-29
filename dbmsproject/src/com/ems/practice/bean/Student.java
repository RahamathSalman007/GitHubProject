package com.ems.practice.bean;

public class Student {

	private int studentId;
	private String studentName;
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	private String mobile;
	private String email;
	private String address;
	private Course course;
	private String courseName;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student() {
	}

	public Student(int studentId, String studentName, String mobile, String email, String address) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
	}

	public int getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return studentId + "\t" + "\t" + studentName + "\t" + "\t" + mobile + "\t" + email + "\t" + address;
	}

}
