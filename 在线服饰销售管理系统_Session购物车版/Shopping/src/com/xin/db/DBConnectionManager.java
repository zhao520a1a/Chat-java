package com.xin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by golden on 2016/11/4 0004.
 * 提供数据库连接
 */
public class DBConnectionManager {
    private String driverName = "org.gjt.mm.mysql.Driver";
    private String url="jdbc:mysql://localhost:3306/jsp_shop?useUnicode=true&characterEncoding=UTF-8";
    private String user="root";
    private String password="root";

    public Connection getConnection(){
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
            return  conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}
