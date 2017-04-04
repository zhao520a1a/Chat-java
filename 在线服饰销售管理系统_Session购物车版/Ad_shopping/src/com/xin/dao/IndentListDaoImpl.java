package com.xin.dao;

import com.xin.bean.Goods;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by golden on 2016/11/13 0013.
 */
public class IndentListDaoImpl extends DataBaseImpl implements IIndentListDao {


    @Override
    public Integer getIndentId(int userid) {
        Integer indentId = null;
        sqlStr = "select max(id) from indent where userid = " + userid;
        try {
            rs = stmt.executeQuery(sqlStr);
            if(rs.next()) {
                indentId = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indentId;
    }

    @Override
    public List<Goods> getIndentListInfo(int indentId) {
        IGoodsDao goodDao = new GoodsDaoImpl();
        List<Goods> goodsList = new ArrayList<Goods>();
        sqlStr = "select goodId,amount from indentlist where indentId = " + indentId;
        try {
            rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                int goodId =  rs.getInt(1);
                Goods goods = goodDao.queryGoodsInfo(goodId);
                goodsList.add(goods);
                int amount = rs.getInt(2);
                goods.setSaleNum(amount);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

    @Override
    public boolean delIndentList(int indentId) {
        boolean flag = false;
        sqlStr = "delete from indentlist where indentId = '" + indentId + "'";
        try {
            flag = stmt.executeUpdate(sqlStr) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }




}
