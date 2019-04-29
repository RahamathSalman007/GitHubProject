package com.ems.practice.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
public static Connection con = null;
	
	public static Connection getConnection(){
		try {
			File f = new File(System.getProperty("user.dir")+"\\src\\com\\ems\\practice\\db\\app.properties");
			FileInputStream fis = new FileInputStream(f);
			Properties p = new Properties();
			p.load(fis);
			Class.forName(p.getProperty("user"));
			con = DriverManager.getConnection(p.getProperty("url"), p.getProperty("uname"), p.getProperty("pwd"));
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
