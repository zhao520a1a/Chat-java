package com.xin.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xin.service.ArticleServiceImpl;
import com.xin.service.IAriticleService;
import com.xin.vo.Article;
import com.xin.vo.PageBean;
import com.xin.vo.User;
 
@WebServlet( 
		name="/article",
		urlPatterns={"/ArticleControl"},
		initParams ={
				@WebInitParam(name="show",value="show.jsp"),
				//刷新页面，Servelt之间转发要加"/"； 
				@WebInitParam(name="query",value="/ArticleControl?action=query&curPage=1&userid=")
		}
)
public class ArticleControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IAriticleService service = new ArticleServiceImpl();  
	private Map<String,String> map = new HashMap<String,String>();
	private static final String SHOW = "show";
	private static final String QUERY = "query";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put(SHOW, config.getInitParameter("show"));
		map.put(QUERY, config.getInitParameter("query"));
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从前端得到页面请求
		String action = request.getParameter("action");
		RequestDispatcher dispatcher = null;
		User user = (User) request.getSession().getAttribute("user");
		
		if(action != null){ //??
		switch(action) { 
		case "query"://分页查询帖子 //table.jsp
			String  user_id = request.getParameter("userid"); //??java.lang.NumberFormatException: null
			int userid = Integer.parseInt(user_id==null ? "999" : user_id);
		
			int curPage = Integer.parseInt(request.getParameter("curPage"));
			
			PageBean pageBean = service.queryArticleByPage( userid, curPage); //从后台得到页面的具体信息
			request.setAttribute("pageBean", pageBean); //通过属性与前端共享信息
			dispatcher = request.getRequestDispatcher(map.get(SHOW));
			break;
		case "del"://删除帖子  page.jsp
			int id = Integer.parseInt(request.getParameter("id"));
			
			if(service.delArticle(id)){
				 //ArticleControl?action=query&curPage=1 (userid==null?999:userid)
				dispatcher = request.getRequestDispatcher(map.get(QUERY) + user.getId());
			}
			break;
		case "add": //增加主帖子 content.jsp
			Article  a = new Article();
			a.setRootid(0); 
			a.setTitle(request.getParameter("title"));
			a.setContent(request.getParameter("content"));
			a.setUser(user);
			if(service.insertArticle(a)) {
				 //ArticleControl?action=query&curPage=1 (userid==null?999:userid)
				dispatcher = request.getRequestDispatcher(map.get(QUERY) + user.getId());
			}
			
		default:
			break;
		}
		dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
