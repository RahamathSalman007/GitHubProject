package com.ems.practice.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.ems.practice.bean.Course;
import com.ems.practice.dao.FeeDAO;
import com.ems.practice.db.DBUtil;
import com.ems.practice.menus.EMSMenus;
import com.ems.practice.query.FeeQueries;
import com.ems.practice.query.StudentQueries;
import static com.ems.practice.db.CheckQueryStatus.isRecordUpdated;
import static com.ems.practice.db.CheckQueryStatus.isStudentIdPresent;
import static com.ems.practice.db.CheckQueryStatus.isRecordInserted;

public class FeeDAOImpl implements FeeDAO {

	static Scanner sc = null;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Connection con = null;
	Course course = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int n = 0;

	@Override
	public void payFee() {
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
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
			System.out.println("Enter Student Id to pay the fee:");
			int sId = sc.nextInt();
			if (isStudentIdPresent(sId)) {
			System.out.println("Enter Amount:");
			String amountPaid = br.readLine();
			while (!Pattern.matches("[0-9]+([,.][0-9]{1,2})?", amountPaid)) {
				System.err.println("Please enter Numbers only for Course Fee!");
				amountPaid = br.readLine();
			}
			double pAmount = Double.parseDouble(amountPaid);
			pst = con.prepareStatement(FeeQueries.isStudentExists);
			pst.setInt(1, sId);
			rs = pst.executeQuery();
			if (rs.next()) {
				double balance = rs.getDouble("balance") - pAmount;
				double paid = rs.getDouble("paid") + pAmount;
				pst = con.prepareStatement(FeeQueries.updateFee);
				pst.setDouble(1, paid);
				pst.setDouble(2, balance);
				pst.setInt(3, sId);
				n = pst.executeUpdate();
				isRecordInserted(n);

			} else {
				pst = con.prepareStatement(FeeQueries.getMaxFeeId);
				rs = pst.executeQuery();
				rs.next();
				int maxFeeid = rs.getInt(1);
				pst = con.prepareStatement(FeeQueries.getStudentCourseFee);
				pst.setInt(1, sId);
				rs = pst.executeQuery();
				rs.next();
				double totalCourseFee = rs.getDouble("fee");
				double balance = totalCourseFee - pAmount;

				pst = con.prepareStatement(FeeQueries.insertFee);
				pst.setInt(1, (maxFeeid + 1));
				pst.setInt(2, sId);
				pst.setDouble(3, pAmount);
				pst.setDouble(4, balance);
				n = pst.executeUpdate();
				isRecordUpdated(n);

			}
			
		} else {
			System.out.println(sId + " Student Id Not Present. Try with another Id!");
			EMSMenus.feeMenu();

		}

		} catch (SQLException | IOException e) {
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
	public void viewFeeOfAStudet(int sId) {
		try {
			if (isStudentIdPresent(sId)) {
			sc = new Scanner(System.in);
			con = DBUtil.getConnection();
			pst = con.prepareStatement(FeeQueries.viewStudentFeeSummary);
			pst.setInt(1, sId);
			rs = pst.executeQuery();
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("|                        STUDENT FEE DETAILS                              |");

			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("ID \t       Name \t       Course \t         Total_Fee \t     Paid \t         Balance");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			rs.next();
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + "\t"
					+ rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			} else {
				System.out.println(sId + " Student Id Not Present. Try with another Id!");
				EMSMenus.feeMenu();

			}
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

	@Override
	public void viewFeeOfAllStudents() {
		try {
			sc = new Scanner(System.in);
			con = DBUtil.getConnection();
			pst = con.prepareStatement(FeeQueries.viewAllStudentFeeSummary);
			rs = pst.executeQuery();
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("|                        STUDENT FEE DETAILS                              |");

			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("ID \t       Name \t       Course \t         Total_Fee \t     Paid \t         Balance");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + "\t"
						+ rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
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
