package com.xin.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
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

	@Override
	public String queryReply(int rootid) {
		 
		List<Article> list = dao.queryReply(rootid); //从贴列表
		String json = JSON.toJSONString(list,true);
 
		return json;
	}
	
}
