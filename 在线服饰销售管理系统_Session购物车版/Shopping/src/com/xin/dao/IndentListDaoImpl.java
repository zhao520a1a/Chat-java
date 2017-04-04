package com.xin.dao;

import com.xin.bean.Goods;
import com.xin.bean.IndentList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by golden on 2016/11/13 0013.
 */
public class IndentListDaoImpl extends DataBaseImpl implements IIndentListDao {

    @Override
    public boolean addIndentList(int userid, List<IndentList> nowbuyList) {
        Integer indentId = this.getIndentId(userid);
        if(indentId != null) {
            for(IndentList iList : nowbuyList) {
                sqlStr = "insert into indentlist(indentId,goodId,amount) values (?,?,?)";
                try {
                    prepstmt = conn.prepareStatement(sqlStr);
                    prepstmt.setInt(1,indentId);
                    prepstmt.setInt(2,iList.getGoodsId());
                    prepstmt.setInt(3,iList.getAmount());
                    prepstmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }

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
}
