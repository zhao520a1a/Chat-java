package com.xin.dao;

import com.xin.bean.Indent;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by golden on 2016/11/13 0013.
 */
public class IndentDaoImpl extends DataBaseImpl implements IIndentDao {
    @Override
    /*生成订单编号，其中为了看起来更有意义，使其生成时包含了userid*/
    public String getIndentNo(int userid) {
        String indentNo = null;
        sqlStr = "select max(id) from indent";
        try {
            rs = stmt.executeQuery(sqlStr);
            if (rs.next()) {
                indentNo = "HYD-" + userid + "-" + rs.getInt(1);
            } else {
                indentNo = "HYD" + userid + "-0";
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indentNo;
    }

    @Override
    public boolean addIndent(String indentNo, int userid, float priceTotal,String content) {
        sqlStr = "insert into indent(indentNo,userId,totalPrice,content,submitTime) values (?,?,?,?,?)";
        try {
            prepstmt = conn.prepareStatement(sqlStr);
            prepstmt.setString(1, indentNo);
            prepstmt.setInt(2, userid);
            prepstmt.setFloat(3, priceTotal);
            prepstmt.setString(4, content);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            prepstmt.setTimestamp(5, timestamp); // 提交订单时间;
            if (prepstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Indent> getIndentInfo(int userid) {
        List<Indent> indents = new ArrayList<Indent>();
        sqlStr = "select * from indent where userid = " + userid;
        try {
            rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                int indentId = rs.getInt(1);
                String indentNo = rs.getString(2);
                float totalPrice = rs.getFloat(4);
                Timestamp sumbitTime = rs.getTimestamp(8);
                Indent indent = new Indent(indentId,indentNo,totalPrice,sumbitTime);
                indents.add(indent);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indents;
    }
}
