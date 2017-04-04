package com.xin.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class Druid {
	private static DataSource dataSource;
	private static Connection conn;
	private static Properties prop; 
	
	static {
		prop = new Properties();
		try {
			prop.load( Druid.class.getClassLoader().getResourceAsStream("druid.ini") );
            dataSource = DruidDataSourceFactory.createDataSource(prop);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Druid() {
	}
	
	public static Connection geteConnection() {
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		System.out.println(Druid.geteConnection());
	}
	
	
	
}
