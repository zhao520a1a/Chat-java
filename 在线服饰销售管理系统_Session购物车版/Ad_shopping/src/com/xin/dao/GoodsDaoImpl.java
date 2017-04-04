package com.xin.dao;

import com.xin.bean.Goods;
import com.xin.bean.GoodsClass;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by golden on 2016/11/6 0006.
 */

public class GoodsDaoImpl extends DataBaseImpl implements IGoodsDao {

    @Override
    public List<Goods> queryGoodsByClass(int goodsClassId) {
        List<Goods> goodlist = new ArrayList<Goods>();
        if (goodsClassId == -1) {
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
                Goods goods = new Goods(goodsId, goodsName, price, img, new GoodsClass(classId, className));
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
                goods = new Goods(goodsId, goodsName, provider, goodsNo, content, price, amount, leave_amount, img, new GoodsClass(goodClassId, className));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public List<Goods> queryAllGoods() {
        List<Goods> goodlist = new ArrayList<Goods>();

        sqlStr = "select goods.id,goodName,price,img,goodsClassId,classname,provider,goodNo,content,amount,leave_amount,regtime from goods,goodsclass where goods.goodsClassId = goodsclass.id;";
        try {
            rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                int goodsId = rs.getInt(1);
                String goodsName = rs.getString(2);
                float price = rs.getFloat(3);
                String img = rs.getString(4);
                int classId = rs.getInt(5);
                String className = rs.getString(6);
                String provider = rs.getString(7);
                String goodsNo = rs.getString(8);
                String content = rs.getString(9);
                int amount = rs.getInt(10);
                int leave_amount = rs.getInt(11);
                Timestamp regtime = rs.getTimestamp(12);

                Goods goods = new Goods(goodsId, goodsName, price, img, new GoodsClass(classId, className), provider, goodsNo, content, amount, leave_amount, regtime);
                goodlist.add(goods);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodlist;
    }

    @Override
    public boolean addGoods(Goods newGoods) {
        boolean flag = false;

        sqlStr = "insert into goods(goodName,price,img,goodsClassId,provider,goodNo,content,amount,leave_amount,regtime) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            prepstmt = conn.prepareStatement(sqlStr);
            prepstmt.setString(1, newGoods.getGoodsName());
            prepstmt.setFloat(2, newGoods.getPrice());
            prepstmt.setString(3, newGoods.getImg());
            prepstmt.setInt(4, newGoods.getGoodsClass().getGoodsClassId());
            prepstmt.setString(5, newGoods.getProvider());
            prepstmt.setString(6, newGoods.getGoodsNo());
            prepstmt.setString(7, newGoods.getContent());
            prepstmt.setInt(8, newGoods.getAmount());
            prepstmt.setInt(9, newGoods.getLeave_amount());
            prepstmt.setTimestamp(10, newGoods.getRegtime());
            flag = prepstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public List<GoodsClass> queryAllGoodsClass() {
        List<GoodsClass> goodsClassList = new ArrayList<GoodsClass>();

        sqlStr = "select  id,classname from goodsclass;";
        try {
            rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                int goodsClassId = rs.getInt(1);
                String goodsClassName = rs.getString(2);
                GoodsClass goodsClass = new GoodsClass(goodsClassId, goodsClassName);
                goodsClassList.add(goodsClass);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsClassList;
    }

    @Override
    public int getGoodsClassId(String classname) {
        int goodsClassId = -1;

        sqlStr = "select  id from goodsclass where classname='" + classname + "'";
        try {
            rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                goodsClassId = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsClassId;
    }

    @Override
    public boolean delGoods(int goodsId) {
        boolean flag = false;
        sqlStr = "delete from goods where id = '" + goodsId + "'";
        try {
            flag = stmt.executeUpdate(sqlStr) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateGoods(Goods goods) {
        boolean flag = false;

        sqlStr = "update goods set goodName='" + goods.getGoodsName() + "'" +
                ",price='" + goods.getPrice() + "'" +
                ", goodsClassId='" + goods.getGoodsClass().getGoodsClassId() + "'   " +
                ",provider='" + goods.getProvider() + "'" +
                ",goodNo='" + goods.getGoodsNo() + "'" +
                ",content='" + goods.getContent() + "'" +
                ",amount='" + goods.getAmount() + "'" +
                " ,leave_amount='" + goods.getLeave_amount() + "'  " +
                 "where id = '" + goods.getGoodsId() + "'" ;
        try {
            flag =   stmt.executeUpdate(sqlStr) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
