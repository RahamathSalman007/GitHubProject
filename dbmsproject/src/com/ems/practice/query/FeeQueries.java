package com.ems.practice.query;

public interface FeeQueries {

	public String getStudentCourseFee = "SELECT c.fee FROM StudentDetails s INNER JOIN Course c ON s.cid=c.cid WHERE s.sId=?";
	public String isStudentExists = "SELECT * FROM Fee WHERE sid=?";
	public String getMaxFeeId = "SELECT MAX(fid) FROM Fee";
	public String insertFee = "INSERT INTO Fee VALUES(?,?,?,?)";
	public String updateFee = "UPDATE Fee SET paid=?, balance=? WHERE sid=?";
	public String viewStudentFeeSummary = "SELECT s.sid, s.sname, c.cname, c.fee, f.paid, f.balance FROM StudentDetails s INNER JOIN Course c ON s.cid=c.cid INNER JOIN fee f ON s.sid=f.sid WHERE s.sid=?";
	public String viewAllStudentFeeSummary = "SELECT s.sid, s.sname, c.cname, c.fee, f.paid, f.balance FROM StudentDetails s INNER JOIN Course c ON s.cid=c.cid INNER JOIN fee f ON s.sid=f.sid";

}
