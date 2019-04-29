package com.ems.practice.dao;

public interface StudentDAO {
	
	public void addStudent();
	public void updateStudent(int sId);
	public void deleteStudent(int sId);
	public void viewStudent(int sId);
	public void viewAllStudents();

}
