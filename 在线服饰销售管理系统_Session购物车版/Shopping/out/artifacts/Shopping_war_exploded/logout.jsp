<%--
  Created by IntelliJ IDEA.
  User: 小鑫哦
  Date: 2016/11/5 0005
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>退出</title>
</head>
<body>
<%
    session.setAttribute("userid",null);
    session.setAttribute("username",null);
    session.setAttribute("shopcar",null);
    session.setAttribute("nowbuyList",null);
    response.sendRedirect("index.jsp");
%>

</body>
</html>
