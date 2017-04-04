package com.xin.dao;

import java.util.List;

import com.xin.vo.Article;
import com.xin.vo.PageBean;

public interface IArticleDAO {
	PageBean queryArticleByPage(int userid, int curPage);
	boolean delArticle(int id);
	boolean insertArticle(Article article);
	List<Article> queryReply(int rootid);
	String queryTitleByid(int id);
}
