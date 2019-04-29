package com.ems.practice.dao.impl;

import static com.ems.practice.db.CheckQueryStatus.isRecordDeleted;
import static com.ems.practice.db.CheckQueryStatus.isRecordInserted;
import static com.ems.practice.db.CheckQueryStatus.isRecordUpdated;
import static com.ems.practice.db.CheckQueryStatus.isStudentIdPresent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.ems.practice.bean.Student;
import com.ems.practice.dao.StudentDAO;
import com.ems.practice.db.DBUtil;
import com.ems.practice.menus.EMSMenus;
import com.ems.practice.query.CourseQueries;
import com.ems.practice.query.StudentQueries;

public class StudentDAOImpl implements StudentDAO {
	static Scanner sc = null;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Connection con = null;
	Student student = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int n = 0;

	@Override
	public void addStudent() {
		try {
			sc = new Scanner(System.in);
			con = DBUtil.getConnection();

			int i = 1;
			while (i == 1) {
				student = new Student();
				System.out.println("Enter Student Id:");
				String sId = br.readLine();
				while (!Pattern.matches("\\d{1,3}", sId)) {
					System.err.println("Please enter Integer only for Student Id!");
					sId = br.readLine();
				}
				int studentID = Integer.parseInt(sId);
				if (!isStudentIdPresent(studentID)) {
					student.setStudentId(studentID);
					System.out.println("Enter Student Name:");
					student.setStudentName(br.readLine());
					System.out.println("Enter Student Mobile:");
					String mobile = br.readLine();
					while (!Pattern.matches("\\d{1,10}", mobile)) {
						System.err.println("Please Enter Valid Mobile Number");
						mobile = br.readLine();
					}
					student.setMobile(mobile);
					System.out.println("Enter Student Email:");
					String email = br.readLine();
					String emailRex = "^([a-z\\d\\.]+)@([a-z\\d]+)\\.([a-z]{3})$";
					while (!Pattern.matches(emailRex, email)) {
						System.err.println("Wrong Email Format. Eg: xyz123.12rd@abc.com");
						email = br.readLine();
					}
					student.setEmail(email);
					System.out.println("Enter Student Address:");
					student.setAddress(br.readLine());
					System.out.println("Select Course Id From below list");
					pst = con.prepareStatement(StudentQueries.getCourseList);
					rs = pst.executeQuery();
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("Course Id \t\t Course Name");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					while (rs.next()) {
						System.out.println(rs.getString(1) + "\t\t" + rs.getString(2));
					}
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					int cId = sc.nextInt();
					pst = con.prepareStatement(CourseQueries.viewCourse);
					pst.setInt(1, cId);
					rs = pst.executeQuery();
					if (!rs.next()) {
						System.out.println("Please Select Correct Course Id");
						cId = sc.nextInt();
					}
					pst = con.prepareStatement(StudentQueries.insertStudent);
					pst.setInt(1, student.getStudentId());
					pst.setString(2, student.getStudentName());
					pst.setString(3, student.getMobile());
					pst.setString(4, student.getEmail());
					pst.setString(5, student.getAddress());
					pst.setInt(6, cId);

					n = pst.executeUpdate();
					isRecordInserted(n);
					if (n != 0) {
						System.out.println("Press 1 to add another Studenr record");
						i = sc.nextInt();
					}

				} else {
					System.out.println(sId + " Student Id is already Present. Try with another Id!");

				}
			}
		} catch (IOException | SQLException e) {
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
	public void updateStudent(int sId) {
		try {
			
			if (isStudentIdPresent(sId)) {
				sc = new Scanner(System.in);
				con = DBUtil.getConnection();
				System.out.println(
						"Select below options to update \n1. Name. \n2. Mobile. \n3. Email. \n4. Address. \n5. Course");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter Name to be updated:");
					pst = con.prepareStatement(StudentQueries.updateStudentName);
					pst.setString(1, br.readLine());
					pst.setInt(2, sId);
					n = pst.executeUpdate();
					isRecordUpdated(n);
					break;

				case 2:
					System.out.println("Enter Mobile to be updated:");

					String mobile = br.readLine();
					while (!Pattern.matches("\\d{1,10}", mobile)) {
						System.err.println("Please Enter Valid Mobile Number");
						mobile = br.readLine();
					}
					pst = con.prepareStatement(StudentQueries.updateStudentmobile);
					pst.setString(1, mobile);
					pst.setInt(2, sId);
					n = pst.executeUpdate();
					isRecordUpdated(n);
					break;
				case 3:
					System.out.println("Enter Email to be updated:");
					String email = br.readLine();
					String emailRex = "^([a-z\\d\\.]+)@([a-z\\d]+)\\.([a-z]{3})$";
					while (!Pattern.matches(emailRex, email)) {
						System.err.println("Wrong Email Format. Eg: xyz123.12rd@abc.com");
						email = br.readLine();
					}
					pst = con.prepareStatement(StudentQueries.updateStudentemail);
					pst.setString(1, email);
					pst.setInt(2, sId);
					n = pst.executeUpdate();
					isRecordUpdated(n);
					break;
				case 4:
					System.out.println("Enter Address to be updated:");
					pst = con.prepareStatement(StudentQueries.updateStudentAddress);
					pst.setString(1, br.readLine());
					pst.setInt(2, sId);
					n = pst.executeUpdate();
					isRecordUpdated(n);
					break;
				case 5:
					System.out.println("Select Course Id From below list");
					pst = con.prepareStatement(StudentQueries.getCourseList);
					rs = pst.executeQuery();
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("Course Id \t\t Course Name");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					while (rs.next()) {
						System.out.println(rs.getString(1) + "\t\t" + rs.getString(2));
					}
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					int cId = sc.nextInt();
					pst = con.prepareStatement(CourseQueries.viewCourse);
					pst.setInt(1, cId);
					rs = pst.executeQuery();
					if (!rs.next()) {
						System.out.println("Please Select Correct Course Id");
						cId = sc.nextInt();
					}
					pst = con.prepareStatement(StudentQueries.updateStudentCourse);
					pst.setInt(1, cId);
					pst.setInt(2, sId);
					n = pst.executeUpdate();
					isRecordUpdated(n);
					break;
				default:
					System.out.println("You have selected wring choice");
					updateStudent(sId);

				}
			} else {
				System.out.println(sId + " Student Id Not Present. Try with another Id!");
				EMSMenus.studentMenu();

			}

		} catch (Exception e) {
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
	public void deleteStudent(int sId) {
		try {
			if (isStudentIdPresent(sId)) {
				sc = new Scanner(System.in);
				con = DBUtil.getConnection();
				pst = con.prepareStatement(StudentQueries.deleteStudent);
				pst.setInt(1, sId);
				n = pst.executeUpdate();
				isRecordDeleted(n);
			} else {
				System.out.println(sId + " Student Id Not Present. Try with another Id!");
				EMSMenus.studentMenu();

			}
		} catch (Exception e) {
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
	public void viewStudent(int sId) {
		try {
			if (isStudentIdPresent(sId)) {
				sc = new Scanner(System.in);
				con = DBUtil.getConnection();
				pst = con.prepareStatement(StudentQueries.viewStudentDetails);
				pst.setInt(1, sId);
				rs = pst.executeQuery();
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(
						"|                           STUDENT DETAILS                                          |");
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Id \t" + "Name \t\t" + "Mobile \t\t" + "Email \t\t" + "Address \t\t" + "Course");
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				rs.next();
				System.out.println(
						rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)

								+ "\t" + rs.getString(5) + "\t" + rs.getString(6));
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			} else {
				System.out.println(sId + " Student Id Not Present. Try with another Id!");
				EMSMenus.studentMenu();
			}
		} catch (Exception e) {
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
	public void viewAllStudents() {
		try {
			sc = new Scanner(System.in);
			con = DBUtil.getConnection();
			pst = con.prepareStatement(StudentQueries.viewAllStudentsDetails);
			rs = pst.executeQuery();
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out
					.println("|                           STUDENT DETAILS                                          |");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Id \t" + "Name \t" + "Mobile \t" + "Email \t" + "Address \t" + "Course");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			while (rs.next()) {
				System.out.println(
						rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)

								+ "\t" + rs.getString(5) + "\t" + rs.getString(6));
				
			}
			System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		} catch (Exception e) {
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
