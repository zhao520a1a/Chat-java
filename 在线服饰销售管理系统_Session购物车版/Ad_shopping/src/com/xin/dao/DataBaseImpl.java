package com.xin.dao;

import com.xin.db.DBConnectionManager;

import java.sql.*;

/**
 * Created by golden on 2016/11/4 0004.
 */
public abstract class DataBaseImpl implements IDataBase {
    protected Connection conn;
    protected Statement stmt = null;
    protected ResultSet rs;
    protected PreparedStatement prepstmt;
    protected String sqlStr;


    public DataBaseImpl() {
        conn = new DBConnectionManager().getConnection();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean insert( ) {
        return false;

    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean query() {
        return false;
    }

    @Override
    public boolean close() {
        try {
            if (prepstmt != null) {
                prepstmt.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
