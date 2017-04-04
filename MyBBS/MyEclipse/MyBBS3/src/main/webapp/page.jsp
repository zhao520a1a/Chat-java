<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<%-- 若是游客，则将用户的id设为999，便于后台数据库通过userid查询 --%>
<c:set var="userid" value="${sessionScope.user.id == null?999:sessionScope.user.id }" />

<div class="pagination">
	<ul>
		<c:if test="${pageBean.curPage == 1 }">
			<li><a href="">首页</a></li>
			<li><a href="">前一页</a></li>
        </c:if>
        
        <c:if test="${pageBean.curPage != 1 }" >
			<li><a href="ArticleControl?action=query&curPage=1&userid=${userid}">
			首页
			</a></li>
			<li><a href="ArticleControl?action=query&curPage=${pageBean.curPage-1}&userid=${userid}">
			前一页
			</a></li>
		</c:if>
        
        <c:forEach var="i" begin="1" end="${pageBean.maxPage}">
			<li><a href="ArticleControl?action=query&curPage=${i}&userid=${userid}">
				${i}
			</a></li>
		</c:forEach>
        
        <c:if test="${pageBean.curPage == pageBean.maxPage}">
        	<li><a href="">下一页</a></li>
			<li><a href="">尾页</a></li>
        </c:if>
         
		<c:if test="${pageBean.curPage != pageBean.maxPage}">
			<li><a href="ArticleControl?action=query&curPage=${pageBean.curPage+1}&userid=${userid}">
			下一页
			</a></li>
			<li><a href="ArticleCoutrol?actionh=query&curPage=${pageBean.maxPage}&userid=${userid}">
			尾页
			</a></li>
		</c:if>
	
	</ul>
</div>