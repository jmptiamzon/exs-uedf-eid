package com.sprint.uedfeid.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Model {
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet rs;
	
	public Model() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
							"", 
							"", 
							""
					);
		
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Driver error: " + e.getMessage());
			
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "SQL error: " + e1.getMessage());
			
		}
	}
	
	
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Connection closing error: " + e.getMessage());
			
		}
	}
	
}
