package com.xin.service;

import com.xin.vo.Article;
import com.xin.vo.PageBean;

public interface IAriticleService {
	PageBean queryArticleByPage(int userid, int curPage) ;
	boolean delArticle(int id) ;
	boolean insertArticle(Article a);
	String queryReply(int rootid) ;
	String queryTitleByid(int id) ;
}
