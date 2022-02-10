package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	private final static String serverName = "localhost";
    private final static String dbName = "pbl4";
    private final static String portNumber = "3306";
    private final static String instance="";
    private final static String userID = "root";
    private final static String password = "";
	
	public static Connection getConnection()throws Exception {
        String url = "jdbc:mysql://"+serverName+":"+portNumber + "/"+dbName;
        
        if(instance == null || instance.trim().isEmpty())
        	url = "jdbc:mysql://"+serverName+":"+portNumber + "/"+dbName;
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, userID, password);
    }   
}
