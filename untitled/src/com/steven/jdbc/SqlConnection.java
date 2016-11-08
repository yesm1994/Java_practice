package com.steven.jdbc;

import java.sql.*;

/**
 * Created by Steven on 2016/11/3.
 */
public class SqlConnection {
    public static void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                    "root","123456");
            PreparedStatement pstmt = connection.prepareStatement("select * from user");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
