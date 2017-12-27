package cn.jhc;

import java.sql.*;

//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import org.h2.command.ddl.CreateTable;
import org.h2.command.dml.Select;

public class Test {
	static {
		try {
			 Class.forName("org.h2.Driver");	
		} catch (ClassNotFoundException e) {
			
		}
	}
	
	private static void createTable() {
		try {
			Class.forName("org.h2.Driver");
			String sql = "create table user(id int auto_increment primary key,"
						+"username varchar(32) not null unique,"
						+"`password` varchar(128) not null)";
	        Connection connection = DriverManager.getConnection("jdbc:h2:~/demo", "sa", "");
			Statement statement = connection.createStatement();
			statement.execute(sql);
			statement.close();
			connection.close();
		} catch (ClassNotFoundException |SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static void insertData() {
		try {
	        Connection connection = DriverManager.getConnection("jdbc:h2:~/demo", "sa", "");
			String sql = "insert into user(username,`password`) values('tom','1234')";
			String sql2 = "insert into user(username,`password`) values('mike','1235')";
			
			Statement statement  = connection.createStatement();
			statement.execute(sql);
			statement.execute(sql2);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private static void select(){
		try {
	        Connection connection = DriverManager.getConnection("jdbc:h2:~/demo", "sa", "");
			String sql = "select username ,`password` from user";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String username = rs.getString(1);
				String pass = rs.getString(2);
				System.out.println("username=" + username +" password=" + pass);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		createTable();
		insertData();
		select();		
	}
	
}
