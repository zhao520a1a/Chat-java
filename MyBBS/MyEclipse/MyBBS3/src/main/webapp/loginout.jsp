<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	session.invalidate();
	response.sendRedirect("ArticleControl?action=query&curPage=1&userid=999");
%>