package com.xin.dao;

/**
 * Created by golden on 2016/11/4 0004.
 */
public interface IDataBase {
    boolean insert()throws  Exception;
    boolean update()throws  Exception;
    boolean delete()throws Exception;
    boolean query()throws  Exception;
    boolean close() throws  Exception;
}
