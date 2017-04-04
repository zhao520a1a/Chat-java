package com.xin.control;

import java.io.IOException;
import java.io.PrintWriter;
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
				@WebInitParam(name="query",value="/ArticleControl?action=query&curPage=1&userid="),//刷新页面，Servelt之间转发要加"/"； 
				@WebInitParam(name="reply",value="/ArticleControl?action=showReply&rootid=")
		}
)
public class ArticleControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IAriticleService service = new ArticleServiceImpl();  
	private Map<String,String> map = new HashMap<String,String>();
	private static final String SHOW = "show";
	private static final String QUERY = "query";
	private static final String REPLY = "reply";
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put(SHOW, config.getInitParameter("show"));
		map.put(QUERY, config.getInitParameter("query"));
		map.put(REPLY, config.getInitParameter("reply"));
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从前端得到页面请求
		String action = request.getParameter("action");
		RequestDispatcher dispatcher = null;
		User user = (User) request.getSession().getAttribute("user");
		
		switch(action) { 
		case "query"://分页查询帖子-->table.jsp
			String  user_id = request.getParameter("userid"); //??java.lang.NumberFormatException: null
			int userid = Integer.parseInt(user_id==null ? "999" : user_id);
		
			int curPage = Integer.parseInt(request.getParameter("curPage"));
			
			PageBean pageBean = service.queryArticleByPage( userid, curPage); //从后台得到页面的具体信息
			request.setAttribute("pageBean", pageBean); //通过属性与前端共享信息
			dispatcher = request.getRequestDispatcher(map.get(SHOW));
			dispatcher.forward(request, response);
			break;
		case "del"://删除帖子  page.jsp
			int id = Integer.parseInt(request.getParameter("id"));
			int rootid1 = Integer.parseInt(request.getParameter("rootid"));
			
			if(service.delArticle(id)){
				if(rootid1 == 0 ) { //删除主贴，刷新主页面
					 //刷新主页面：ArticleControl?action=query&curPage=1&userid==?
					dispatcher = request.getRequestDispatcher(map.get(QUERY) + user.getId());
				} else { //删除从贴,刷新从贴页面
					//刷新页面： AriticleControl?action=showReply&rootid=?
					dispatcher = request.getRequestDispatcher(map.get(REPLY) + rootid1);
				}
			}
			dispatcher.forward(request, response);
			break;
		case "add": //增加主帖子 content.jsp
			Article  a1 = new Article();
			a1.setRootid(0); 
			a1.setTitle(request.getParameter("title"));
			a1.setContent(request.getParameter("content"));
			a1.setUser(user);
			if(service.insertArticle(a1)) {
				 //刷新主页面：ArticleControl?action=query&curPage=1 (userid==null?999:userid)
				dispatcher = request.getRequestDispatcher(map.get(QUERY) + user.getId());
			}
			dispatcher.forward(request, response);
			break;
		case "addReply"://增加从帖子 
			Article a2 = new Article();
			int rootid2 = Integer.parseInt(request.getParameter("rootid"));
			a2.setRootid(rootid2);
			a2.setTitle(request.getParameter("title"));
			a2.setContent(request.getParameter("content"));
			a2.setUser(user);
			if(service.insertArticle(a2)) {
				//刷新页面：AriticleControl?action=showReply&rootid=?
				dispatcher = request.getRequestDispatcher(map.get(REPLY) + rootid2);
			}
			dispatcher.forward(request, response);
			break;
		case "showReply": //显示从帖子，使用AJAX技术，异步传数据
			int rootid = Integer.parseInt(request.getParameter("rootid"));
			String result = service.queryReply(rootid);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.write(result);
			out.flush();
			out.close();
			break;
		default:
			break;
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
