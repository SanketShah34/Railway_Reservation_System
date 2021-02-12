package com.project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
//@RestController
//@RequestMapping("/loginInfo")
public class loginController {
	// @Autowired
	// private UserRepository userRepository;

	@PostMapping(value = "/getUser")
	public String getUser(@RequestParam("cusId") int cusId, @RequestParam("password") String password, Model model) {

		String result = getUserFromDB(cusId, password);

		model.addAttribute("theString", result);
		return "login";
	}

	private String getUserFromDB(int cuId, String password) {
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/railway_system?useSSL=false";
		final String USER = "root";
		final String PASS = "Mysql@123";
		Statement stmt = null;
		int idToReturn = -1;
		System.out.println("Hello");
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "select * FROM user";
			ResultSet rs = stmt.executeQuery(sql);
			int flag = 0;
			while (rs.next()) {
				int id = rs.getInt(1);
				String pwd = rs.getString(3);
				if (id == cuId && pwd.equals(password)) {
					flag = 1;
					idToReturn = id;
					break;
				}
			}
			rs.close();
			stmt.close();
			conn.close();
			if (flag == 1) {
				return "valid";
			} else {
				return "invalid";
			}
		}

		catch (Exception e) {
			System.out.println(e);
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			/*
			 * try{ if(conn!=null) conn.close(); }catch(SQLException se){ }
			 */// end finally try
		} // end try
		return String.valueOf(idToReturn);
		// TODO Auto-generated method stub

	}
}

