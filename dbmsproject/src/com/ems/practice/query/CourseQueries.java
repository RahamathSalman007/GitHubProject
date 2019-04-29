package com.ems.practice.query;

public interface CourseQueries {
	
	public String insertCourse = "INSERT INTO Course VALUES (?,?,?,?)";
	public String updateCourseName = "UPDATE Course SET cname=? WHERE cid=?";
	public String updateCourseDuration = "UPDATE Course SET cduration=? WHERE cid=?";
	public String updateCourseFee = "UPDATE Course SET fee=? WHERE cid=?";
	public String deleteCourse = "DELETE FROM Course WHERE cid=?";
	public String viewCourse = "SELECT * FROM Course WHERE cid=?";
	public String viewAllCourses = "SELECT * FROM Course";

}
