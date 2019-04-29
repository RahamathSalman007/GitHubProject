package com.ems.practice.query;

public interface StudentQueries {
	
	public String getCourseList = "SELECT cid, cname FROM Course";
	public String insertStudent = "INSERT INTO StudentDetails VALUES (?,?,?,?,?,?)";
	public String updateStudentName = "UPDATE StudentDetails SET sname=? WHERE sId=?";
	public String updateStudentmobile = "UPDATE StudentDetails SET mobile=? WHERE sId=?";
	public String updateStudentemail = "UPDATE StudentDetails SET email=? WHERE sId=?";
	public String updateStudentAddress = "UPDATE StudentDetails SET address=? WHERE sId=?";
	public String updateStudentCourse = "UPDATE StudentDetails SET cid=? WHERE sId=?";
	public String deleteStudent = "DELETE FROM StudentDetails WHERE sId=?";
	public String viewStudent = "SELECT * FROM StudentDetails WHERE sId=?";
	public String viewStudentDetails = "SELECT s.sid, s.sname, s.mobile, s.email, s.address, c.cname FROM StudentDetails s INNER JOIN Course c ON s.cid=c.cid WHERE s.sId=?";
	public String viewAllStudentsDetails = "SELECT s.sid, s.sname, s.mobile, s.email, s.address, c.cname FROM StudentDetails s INNER JOIN Course c ON s.cid=c.cid";

}
