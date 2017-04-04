package com.xin.dao;

import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.xin.db.Druid;
import com.xin.vo.Article;
import com.xin.vo.PageBean;
import com.xin.vo.User;

public class ArticleDAOImpl implements IArticleDAO {

	private Connection conn;
	
	public ArticleDAOImpl() {
		conn = Druid.geteConnection();
	}
	
	
	@Override
	public PageBean queryArticleByPage(int userid, int curPage) {
		String sql = "call q1(?,?,?,?,?)";
		CallableStatement cs =  null;
		ResultSet rs = null;
		PageBean pageBean = null;
		
		try {
			cs = conn.prepareCall(sql);
			cs.setInt(1, userid);
			cs.setInt(2, curPage);
			cs.registerOutParameter(3, java.sql.Types.INTEGER);
			cs.registerOutParameter(4, java.sql.Types.INTEGER);
			cs.registerOutParameter(5, java.sql.Types.INTEGER);
			boolean flag = cs.execute(); //执行储存过程
			while(flag) {
				/*数据库中的查询出值就传入PageBean中；*/
				pageBean = new PageBean();
				pageBean.setCurPage(curPage);
				pageBean.setRowsPerPage( cs.getInt(3));
				pageBean.setMaxRowCount( cs.getInt(4));
				pageBean.setMaxPage(cs.getInt(5));
				//当前页中每行数据信息的集合 
				ArrayList<Article> list = new ArrayList<Article>();
				rs = cs.getResultSet();
				while(rs.next()) {
					//先得到每行的数据信息
					Article article = new Article();
					article.setId(rs.getInt("a.id")); //a是数据库中article表的别名
					article.setRootid(rs.getInt("a.rootid"));
					article.setTitle(rs.getString("a.title"));
					article.setContent(rs.getString("a.content"));
					article.setDatetime(rs.getString("a.datetime"));
					User user = new User();
					user.setId(rs.getInt("b.id")); //b是数据库中bbsuser表的别名
					article.setUser(user);
					
					list.add(article);
				}
				pageBean.setData(list);
				
				flag = cs.getMoreResults();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if(cs != null) {
					cs.close();
				}
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pageBean;
	}
	
	public static void main(String[] args) {
		ArticleDAOImpl dao=new ArticleDAOImpl();
		dao.queryArticleByPage(1, 999);
	}
	

	@Override
	public boolean delArticle(int id) {
		
		String sql = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			if(id == 0) { //删除主贴：注意主贴删除，跟其相关的跟帖也应该删除；
				sql = "delete from article where id=? or rootid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setInt(2, id);
			} else{ //删除从贴
				sql = "delete from article where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
			}
			flag = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	    return flag;
	}

    
	@Override
	public boolean insertArticle(Article a) {
		String sql = "insert into article(rootid,title,content,datetime,userid) " 
	                  +"values(?, ?, ?, now(), ?)";
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getRootid());
			pstmt.setString(2, a.getTitle());
			StringReader reader = new StringReader(a.getContent());  //向数据库中写入clob【oracle中】/longtext【mysql中】类型的content数据；
			pstmt.setCharacterStream(3, reader, a.getContent().length());
			pstmt.setInt(4, a.getUser().getId());
			
			flag = pstmt.executeUpdate()>0;
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return flag;
	}

    
	//查询回帖
	@Override
	public List<Article> queryReply(int rootid) {
		String sql = "select * from article a inner join bbsuser b on(a.userid = b.id)  where rootid = ?";
		PreparedStatement pstat = null; 
		ResultSet result = null;
		List<Article> list = new LinkedList();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, rootid);
			result = pstat.executeQuery();
			while(result.next()) {
				Article article = new Article();
				article.setId(result.getInt("a.id"));
				article.setRootid(result.getInt("a.rootId"));
				article.setTitle(result.getString("a.title"));
				//设置clob
				article.setContent(result.getString("a.content"));
				User user = new User();
				user.setId(result.getInt("b.id"));
				article.setUser(user);
				list.add(article);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstat != null) {
				try {
					pstat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}

    //查询主贴的标题
	@Override
	public String queryTitleByid(int id) {
		String sql = "select title from article where id=?";
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String title = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeQuery();
			while(result.next()) {
				title = result.getString("title");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} 
		
		return title;
	}

	
	
}
