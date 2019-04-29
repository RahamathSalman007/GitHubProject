package com.ems.practice.dao.impl;

import static com.ems.practice.db.CheckQueryStatus.isRecordDeleted;
import static com.ems.practice.db.CheckQueryStatus.isRecordUpdated;
import static com.ems.practice.db.CheckQueryStatus.isRecordInserted;
import static com.ems.practice.db.CheckQueryStatus.isCourseIdPresent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.ems.practice.bean.Course;
import com.ems.practice.dao.CourseDAO;
import com.ems.practice.db.DBUtil;
import com.ems.practice.menus.EMSMenus;
import com.ems.practice.query.CourseQueries;

public class CourseDAOImpl implements CourseDAO {

	static Scanner sc = null;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Connection con = null;
	Course course = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int n = 0;

	@Override
	public void addCource() {
		
		try {
			sc = new Scanner(System.in);
			con = DBUtil.getConnection();
			course = new Course();
			int i = 1;
			while (i == 1) {

				System.out.println("Enter Course Id:");
				String cId = br.readLine();
				while (!Pattern.matches("\\d{1,3}", cId)) {
					System.err.println("Please enter Integer only for Course Id!");
					cId = br.readLine();
				}
				int courseID = Integer.parseInt(cId);
				if(!isCourseIdPresent(courseID)){
				course.setCourseId(courseID);
				System.out.println("Enter Course Name:");
				course.setCourseName(br.readLine());
				System.out.println("Enter Course Duration:");
				course.setCourseDuration(br.readLine());
				System.out.println("Enter Course Fee");
				String fee = br.readLine();
				while (!Pattern.matches("[0-9]+([,.][0-9]{1,2})?", fee)) {
					System.err.println("Please enter Numbers only for Course Fee!");
					fee = br.readLine();
				}
				double coursefee = Double.parseDouble(fee);
				course.setCourseFee(coursefee);
				pst = con.prepareStatement(CourseQueries.insertCourse);
				pst.setInt(1, course.getCourseId());
				pst.setString(2, course.getCourseName());
				pst.setString(3, course.getCourseDuration());
				pst.setDouble(4, course.getCourseFee());
				int n = pst.executeUpdate();
				isRecordInserted(n);
				if (n != 0) {
					System.out.println("Press 1 to add another course.");
					i = sc.nextInt();
				}

			
			} else {
				System.out.println(cId + " Course Id is already Present. Try with another Id!");
				
			}
		}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InputMismatchException ime) {
			System.out.println("You have entered wrong data. Please Enter Correct Data!");
			addCource();
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateCourse(int cId) {

		try {
			if(isCourseIdPresent(cId)){
			sc = new Scanner(System.in);
			con = DBUtil.getConnection();
			System.out.println("Select below options to update \n1. Course Name. \n2. Course Duration. \n3. Fee");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter the Course Name to be updated:");
				pst = con.prepareStatement(CourseQueries.updateCourseName);
				pst.setString(1, br.readLine());
				pst.setInt(2, cId);
				n = pst.executeUpdate();
				isRecordUpdated(n);
				break;

			case 2:
				System.out.println("Enter the Course Duration to be updated:");
				pst = con.prepareStatement(CourseQueries.updateCourseDuration);
				pst.setString(1, br.readLine());
				pst.setInt(2, cId);
				n = pst.executeUpdate();
				isRecordUpdated(n);
				break;
			case 3:
				System.out.println("Enter the Course Fee to be updated:");
				
				String fee = br.readLine();
				while (!Pattern.matches("[0-9]+([,.][0-9]{1,2})?", fee)) {
					System.err.println("Please enter Numbers only for Course Fee!");
					fee = br.readLine();
				}
				double coursefee = Double.parseDouble(fee);
				pst = con.prepareStatement(CourseQueries.updateCourseFee);
				pst.setDouble(1, coursefee);
				pst.setInt(2, cId);
				n = pst.executeUpdate();
				isRecordUpdated(n);
				break;
			default:
				System.out.println("You have selected wrong choice");
				updateCourse(cId);
			}
			} else {
				System.out.println(cId + " Course Id Not Present. Try with another Id!");
				EMSMenus.courseMenu();
			}

		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InputMismatchException ime) {
			System.out.println("You have entered wrong data. Please Enter Correct Data!");
			addCource();
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void deleteCourse(int cId) {
		try {
			if(isCourseIdPresent(cId)){
			sc = new Scanner(System.in);
			con = DBUtil.getConnection();
			pst = con.prepareStatement(CourseQueries.deleteCourse);
			pst.setInt(1, cId);
			n = pst.executeUpdate();
			isRecordDeleted(n);
			} else {
				System.out.println(cId + " Course Id Not Present. Try with another Id!");
				EMSMenus.courseMenu();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void viewCourse(int cId) {
		try {
			if(isCourseIdPresent(cId)){
		
			con = DBUtil.getConnection();
			pst = con.prepareStatement(CourseQueries.viewCourse);
			pst.setInt(1, cId);
			rs = pst.executeQuery();
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Course Id \t Course Name \t Course Duration \t Course Fee");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			rs.next();
			System.out.println(
					rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t" + rs.getString(4));
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			} else {
				System.out.println(cId + " Course Id Not Present. Try with another Id!");
				EMSMenus.courseMenu();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void viewAllCources() {
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(CourseQueries.viewAllCourses);
			rs = pst.executeQuery();

			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Course Id \t Course Name \t Course Duration \t Course Fee");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			while (rs.next()) {
				System.out.println(
						rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t" + rs.getString(4));
			}
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
