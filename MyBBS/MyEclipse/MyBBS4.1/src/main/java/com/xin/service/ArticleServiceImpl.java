package com.xin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("title", this.queryTitleByid(rootid));
		map.put("list", dao.queryReply(rootid)); //回帖列表
		String json = JSON.toJSONString(map,true);
 
		return json;
	}
	
 
	@Override
	//使用了后台数据库查询主贴的标题
	public String queryTitleByid(int id) {
		return dao.queryTitleByid(id);
	}
 
	
}
