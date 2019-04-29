package com.ems.practice.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ems.practice.dao.impl.CourseDAOImpl;
import com.ems.practice.dao.impl.FeeDAOImpl;
import com.ems.practice.dao.impl.StudentDAOImpl;
import com.ems.practice.main.EMS;

public class EMSMenus {

	static Scanner sc = null;
	static CourseDAOImpl courseDAOImpl = new CourseDAOImpl();
	static StudentDAOImpl studentDAOImpl = new StudentDAOImpl();
	static FeeDAOImpl feeDAOImpl = new FeeDAOImpl();

	public static void adminMenu() {
		try {
			sc = new Scanner(System.in);
		System.out.println("-------------------");
		System.out.println("|   ADMIN MENU    |");
		System.out.println("-------------------");
		System.out.println("|  1. COURSE      |");
		System.out.println("|  2. FEE         |");
		System.out.println("|  3. STUDENT     |");
		System.out.println("|  4. REPORTS     |");
		System.out.println("|  5. BACK        |");
		System.out.println("-------------------");
		System.out.println("Enter Your Choice ?");
		int choice = sc.nextInt();
		
		switch (choice) {
		case 1:
			courseMenu();
			break;
		case 2:
			feeMenu();
			break;
		case 3:
			studentMenu();
			break;
		case 4:
			reportsMenu();
			break;
		case 5:
			EMS.main(null);
			break;
		default:
			System.out.println("Please select choice range from 1 - 5 only!");
			adminMenu();
			
		} 
	}catch (InputMismatchException e) {
		System.out.println("Please enter digits only to select the choice.");
		adminMenu();
		
	
	}
	}

	public static void userMenu() {
		try {
		sc = new Scanner(System.in);
		System.out.println("-------------------");
		System.out.println("|   USER MENU     |");
		System.out.println("-------------------");
		System.out.println("|  1. STUDENT     |");
		System.out.println("|  2. FEE         |");
		System.out.println("|  3. BACK        |");
		System.out.println("-------------------");
		System.out.println("Enter Your Choice ?");
		int choice = sc.nextInt();
		
		switch (choice) {
		case 1:
			studentMenu();;
			break;
		case 2:
			feeMenu();
			break;
		case 3:
			EMS.main(null);
			break;
		default:
			System.out.println("Please select choice range from 1 - 3 only!");
			userMenu();
		}
		} catch (InputMismatchException e) {
			System.out.println("Please enter digits only to select the choice.");
			userMenu();
		}
	}

	public static void studentMenu() {
		int sId = 0;
		try {
		sc = new Scanner(System.in);
		System.out.println("-------------------------");
		System.out.println("|   STUDENT MENU        |");
		System.out.println("-------------------------");
		System.out.println("|  1. ADD STUDENT       |");
		System.out.println("|  2. VIEW STUDENT      |");
		System.out.println("|  3. VIEW ALL STUDENTS |");
		System.out.println("|  4. UPDATE STUDENT    |");
		System.out.println("|  5. DELETE STUDENT    |");
		System.out.println("|  6. BACK              |");
		System.out.println("-------------------------");
		System.out.println("Enter Your Choice ?");
		int choice = sc.nextInt();
		
		try{
		switch (choice) {
		case 1:
			studentDAOImpl.addStudent();
			studentMenu();
			break;
		case 2:
			System.out.println("Enter Student Id to view details:");
			sId = sc.nextInt();
			studentDAOImpl.viewStudent(sId);
			studentMenu();
			break;
		case 3:
			studentDAOImpl.viewAllStudents();
			studentMenu();
			break;
		case 4:
			System.out.println("Enter Student Id to Update the details:");
			sId = sc.nextInt();
			studentDAOImpl.updateStudent(sId);
			studentMenu();
			break;
		case 5:
			System.out.println("Enter Student Id to delete Student:");
			sId = sc.nextInt();
			studentDAOImpl.deleteStudent(sId);
			studentMenu();
			break;
		case 6:
			EMS.main(null);
			break;
		default:
			System.out.println("Please select choice range from 1 - 6 only!");
			studentMenu();

		}
		}catch(InputMismatchException e){
			System.out.println("Student Id is Integer Value. Please Enter Integer Only!");
			studentMenu();
		}
		} catch (InputMismatchException e) {
			System.out.println("Please enter digits only to select the choice.");
			studentMenu();
		}
	}

	public static void feeMenu() {
		try {
		sc = new Scanner(System.in);
		System.out.println("----------------------------------------");
		System.out.println("|        FEE MENU                      |");
		System.out.println("----------------------------------------");
		System.out.println("|  1. PAY FEE                          |");
		System.out.println("|  2. VIEW FEE DETAILS OF A STUDENT    |");
		System.out.println("|  3. VIEW FEE DETAILS OF ALL STUDENTS |");
		System.out.println("|  4. BACK                             |");
		System.out.println("----------------------------------------");
		System.out.println("Enter Your Choice ?");
		int choice = sc.nextInt();
		try {
		switch (choice) {
		case 1:
			feeDAOImpl.payFee();
			feeMenu();
			break;
		case 2:System.out.println("Enter Student Id to View Fee Details");
			feeDAOImpl.viewFeeOfAStudet(sc.nextInt());
			feeMenu();
			break;
		case 3:
			feeDAOImpl.viewFeeOfAllStudents();
			feeMenu();
			break;
		case 4:
			EMS.main(null);
			break;
		default:
			System.out.println("Please select choice range from 1 - 4 only!");
			feeMenu();

		}
		}catch(InputMismatchException e){
			System.out.println("Student Id is Integer Value. Please Enter Integer Only!");
			feeMenu();
		}
		} catch (InputMismatchException e) {
			System.out.println("Please enter digits only to select the choice.");
			feeMenu();
		}
	}

	public static void courseMenu() {
		try {
		sc = new Scanner(System.in);
		System.out.println("-------------------------");
		System.out.println("|   COURSE MENU         |");
		System.out.println("-------------------------");
		System.out.println("|  1. ADD COURSE        |");
		System.out.println("|  2. VIEW COURSE       |");
		System.out.println("|  3. VIEW ALL COURSES  |");
		System.out.println("|  4. UPDATE COURSE     |");
		System.out.println("|  5. DELETE COURSE     |");
		System.out.println("|  6. BACK              |");
		System.out.println("-------------------------");
		System.out.println("Enter Your Choice ?");
		int choice = sc.nextInt();
		try {
		switch (choice) {
		case 1:
			courseDAOImpl.addCource();
			courseMenu();
			break;
		case 2:
			System.out.println("Enter Course Id to View the Details");
			courseDAOImpl.viewCourse(sc.nextInt());
			courseMenu();
			break;
		case 3:
			courseDAOImpl.viewAllCources();
			courseMenu();
			break;
		case 4:
			System.out.println("Enter Course Id to update the Details");
			courseDAOImpl.updateCourse(sc.nextInt());
			courseMenu();
			break;
		case 5:
			System.out.println("Enter Course Id to update the Details");
			courseDAOImpl.deleteCourse(sc.nextInt());
			courseMenu();
			break;
		case 6:
			EMS.main(null);
			break;
		default:
			System.out.println("Please select choice range from 1 - 6 only!");
			 courseMenu();
		}
		}catch(InputMismatchException e){
			System.out.println("Course Id is Integer Value. Please Enter Integer Only!");
			courseMenu();
		}
		
		} catch (InputMismatchException e) {
			System.out.println("Please enter digits only to select the choice.");
			courseMenu();
		}
	}

	public static void reportsMenu() {
		
		sc = new Scanner(System.in);
		System.out.println("Under Development");
		adminMenu();
		/*
		 * System.out.println("-------------------"); System.out.println(
		 * "|  REPORTS MENU   |"); System.out.println("-------------------");
		 * System.out.println("|  1. COURSE      |"); System.out.println(
		 * "|  2. FEE         |"); System.out.println("|  3. STUDENT     |");
		 * System.out.println("|  4. REPORTS     |"); System.out.println(
		 * "|  5. BACK        |"); System.out.println("-------------------");
		 * System.out.println("Enter Your Choice ?"); int choice = sc.nextInt();
		 */
	}
}
