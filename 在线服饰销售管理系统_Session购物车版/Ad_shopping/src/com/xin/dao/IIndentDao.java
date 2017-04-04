package com.xin.dao;

import com.xin.bean.Indent;

import java.util.List;

/**
 * Created by golden on 2016/11/13 0013.
 * 订单和用户的关联的操作类
 */
public interface IIndentDao {
    String getIndentNo(int userid);

    List<Indent> getIndentInfo(int userid);

    boolean delIndent(int indentId);

    List<Indent> getAllIndents();
}
