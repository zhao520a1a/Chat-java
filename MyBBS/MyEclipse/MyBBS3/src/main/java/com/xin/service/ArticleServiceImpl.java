package com.xin.service;

import com.xin.dao.ArticleDAOImpl;
import com.xin.dao.IArticleDAO;
import com.xin.vo.Article;
import com.xin.vo.PageBean;

public class ArticleServiceImpl implements IAriticleService {
	private IArticleDAO dao = new ArticleDAOImpl();
    	
	@Override
	public PageBean queryArticleByPage(int userid, int curPage) {
		return dao.queryArticleByPage(userid, curPage);
	}

	@Override
	public boolean delArticle(int id) {
		return dao.delArticle(id);
	}

	@Override
	public boolean insertArticle(Article a) {
		return dao.insertArticle(a);
	}

}
