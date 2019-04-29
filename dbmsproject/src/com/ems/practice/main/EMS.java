package com.ems.practice.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ems.practice.menus.EMSMenus;

public class EMS {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		  System.out.println("-------------------------");
		  System.out.println("|   STUDENT MANAGEMENT  |");
		  System.out.println("-------------------------");
		  System.out.println("|  1. ADMIN             |");
		  System.out.println("|  2. USER              |");
		  System.out.println("|  3. EXIT              |");
		  System.out.println("-------------------------");
		  System.out.println("Enter Your Choice ?");
		  int choice = 0;
		try {
			choice = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Please enter digits only to select the choice.");
			main(null);
		}
		  switch(choice){
		  case 1:EMSMenus.adminMenu();
		  break;
		  case 2:EMSMenus.userMenu();
		  break;
		  case 3: System.exit(0);
		  break;
		  default: System.out.println("Select choice range from 1 - 3 only!");
		  main(null);
		  }
		}

	

}
