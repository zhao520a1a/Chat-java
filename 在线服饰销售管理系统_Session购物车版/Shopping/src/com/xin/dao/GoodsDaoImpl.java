package com.xin.dao;

import com.xin.bean.Goods;
import com.xin.bean.GoodsClass;
import com.xin.bean.IndentList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by golden on 2016/11/6 0006.
 */
public class GoodsDaoImpl extends DataBaseImpl implements IGoodsDao {


    @Override
    public List<Goods> queryGoodsByClass(int goodsClassId) {
        List<Goods> goodlist = new ArrayList<Goods>();
        if(goodsClassId == -1) {
            sqlStr = "select goods.id,goodName,price,img,goodsClassId,classname from goods,goodsclass where goods.goodsClassId = goodsclass.id;";
        } else {
            sqlStr = "select goods.id,goodName,price,img,goodsClassId,classname from goods,goodsclass where goods.goodsClassId = goodsclass.id and goodsClassId =" + goodsClassId + ";";
        }
        try {
            rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                int goodsId = rs.getInt(1);
                String goodsName = rs.getString(2);
                float price = rs.getFloat(3);
                String img = rs.getString(4);
                int classId = rs.getInt(5);
                String className = rs.getString(6);
                Goods goods = new Goods(goodsId, goodsName, price, img, new GoodsClass(classId,className));
                goodlist.add(goods);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodlist;
    }

    @Override
    public Goods queryGoodsInfo(int goodsId) {
        Goods goods = null;
        sqlStr = "select * from goods where id=" + goodsId;
        sqlStr = " select goodname,provider,goodNo,Content,price,amount,leave_amount,img, goodsClassId,classname from goods,goodsclass where  goods.goodsClassId = goodsclass.id and goods.id=" + goodsId;
        try {
            rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                String goodsName = rs.getString(1);
                String provider = rs.getString(2);
                String goodsNo = rs.getString(3);
                String content = rs.getString(4);
                float price = rs.getFloat(5);
                int amount = rs.getInt(6);
                int leave_amount = rs.getInt(7);
                String img = rs.getString(8);
                int goodClassId = rs.getInt(9);
                String className = rs.getString(10);
                goods = new Goods(goodsId, goodsName, provider, goodsNo, content, price, amount, leave_amount, img, new GoodsClass(goodClassId,className));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public int getLeaveAmount(int goodsId) {
        int leaveAmount = 0;
        sqlStr = "select leave_amount from goods where id =  " + goodsId;
        try {
            rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                leaveAmount = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaveAmount;
    }



    @Override
    public boolean updateLeaveAmount(List<IndentList> nowbuyList ) {
        for(IndentList  iList : nowbuyList) {
            sqlStr = "update goods set leave_amount=leave_amount-" + iList.getAmount() + " where id=" + iList.getGoodsId();
            try {
                 stmt.executeUpdate(sqlStr);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


}
