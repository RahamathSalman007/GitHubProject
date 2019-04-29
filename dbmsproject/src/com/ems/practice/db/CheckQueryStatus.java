package com.ems.practice.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ems.practice.query.CourseQueries;
import com.ems.practice.query.StudentQueries;

public class  CheckQueryStatus{
	
	static Connection con = null;
	static PreparedStatement pst = null;
	static ResultSet rs = null;
	
	public static void isRecordUpdated(int n){
		if (n != 0) {
			System.out.println("Record updated successfully.");
		} else {
			System.out.println("Record not updated. Please try again!");
		}
	}
	
	
	public static void isRecordDeleted(int n){
		if (n != 0) {
			System.out.println("Record deleted successfully.");
		} else {
			System.out.println("Record not deleted. Please try again!");
		}
	}
	
	public static void isRecordInserted(int n){
		if (n != 0) {
			System.out.println("Record inserted successfully.");
		} else {
			System.out.println("Record not inserted. Please try again!");
		}
	}
	
	
	public static boolean isStudentIdPresent(int sId) {
		boolean sIdPresent = false;
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(StudentQueries.viewStudent);
			pst.setInt(1, sId);
			rs = pst.executeQuery();
			if(rs.next()) {
				sIdPresent = true;
			}else {
				sIdPresent = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return sIdPresent;
	}
	
	public static boolean isCourseIdPresent(int cId) {
		boolean cIdPresent = false;
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(CourseQueries.viewCourse);
			pst.setInt(1, cId);
			rs = pst.executeQuery();
			
				if (rs.next()) {
					cIdPresent = true;
					
				}else {
					cIdPresent = false;
				}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return cIdPresent;
		
	}

}
