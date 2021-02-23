package com.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTemplate {
   
   public static Connection getConnection() {
      Connection conn=null;
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
        		 "student","student");
         conn.setAutoCommit(false);
      }catch(Exception e) {
         e.printStackTrace();
      }return conn;
   }

}
