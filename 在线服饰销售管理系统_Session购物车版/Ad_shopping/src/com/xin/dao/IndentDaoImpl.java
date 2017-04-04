package com.xin.dao;

import com.xin.bean.Indent;
import com.xin.bean.ShopUser;

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


    @Override
    public List<Indent> getAllIndents() {
        List<Indent> indents = new ArrayList<Indent>();
        sqlStr = "select indent.id,indentNo,totalPrice,content,isPayoff,isSales,submitTime,users.id,users.username from indent,users where indent.userId=users.id ";

        try {
            rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                int indentId = rs.getInt(1);
                String indentNo = rs.getString(2);
                Float totalPrice = rs.getFloat(3);
                String content = rs.getString(4);
                String isPayoff = rs.getString(5);
                String isSales = rs.getString(6);
                Timestamp submitTime = rs.getTimestamp(7);
                int userId = rs.getInt(8);
                String username = rs.getString(9);

                Indent indent = new Indent(indentId,indentNo,totalPrice,content,isPayoff,isSales,submitTime,new ShopUser(userId,username));
                indents.add(indent);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indents;

    }


    @Override
    public boolean delIndent(int indentId) {
        boolean flag = false;
        sqlStr = "delete from indent where id = '" + indentId + "'";
        try {
            flag = stmt.executeUpdate(sqlStr) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


}
