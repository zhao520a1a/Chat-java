package com.xin.dao;

import com.xin.vo.Article;
import com.xin.vo.PageBean;

public interface IArticleDAO {
	PageBean queryArticleByPage(int userid, int curPage);
	boolean delArticle(int id);
	boolean insertArticle(Article article);
}
