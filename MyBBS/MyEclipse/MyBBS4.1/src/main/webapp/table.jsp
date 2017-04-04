<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="contentwrapper">
	<div class="main_content">
		<%@include file="top.jsp"%>


		<div class="row-fluid">
			<div class="span12">
				<h3 class="heading">快来灌水</h3>


				<div class="alert alert-error">
					<a class="close" data-dismiss="alert">×</a> <strong> 操作信息:
						${requestScope.msg !=null ? requestScope.msg : "" } 快来和小伙伴们一起灌水吧！
					</strong>

				</div>

				<%-- 只用登录的用户才能设置每页显示的行数 --%>
				<c:if test="${sessionScope.user != null}">
					<c:set var="uid" value="${sessionScope.user.id}" />

					<div class="btn-group sepH_b">
						<button data-toggle="dropdown" class="btn dropdown-toggle">
							行数 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">


							<li><a href="UserControl?action=page&row=5&userid=${uid}">默认5行</a></li>
							<li><a href="UserControl?action=page&row=10&userid=${uid}">每页10行</a></li>
							<li><a href="UserControl?action=page&row=2&userid=${uid}">每页2行</a></li>
						</ul>
					</div>

				</c:if>




				<table class="table table-bordered table-striped table_vam"
					id="dt_gal">
					<thead>
						<tr>
							<th class="table_checkbox">序号</th>
							<th style="width: 50px">发布人</th>
							<th style="width: 100px">主帖标题</th>
							<th style="width: 420px">主帖内容</th>
							<th style="width: 60px">发布日期</th>
							<th style="width: 60px">操作</th>
						</tr>
					</thead>
					<tbody>

						<c:set var="pageBean" value="${requestScope.pageBean}" />
						<c:forEach var="article" items="${pageBean.data}"
							varStatus="status">

							<tr>
								<td>${status.count}</td>
								<td><a href="upload/.jpg" title=""
									class="cbox_single thumbnail"> <%--请求得到用户的头像 --%> <img
										src="UserControl?action=pic&id=${article.user.id}" alt=""
										style="height: 50px; width: 50px" />
								</a></td>

								<td><a href=""> ${article.title} </a></td>

								<td>${article.content}</td>
								<td>${article.datetime}</td>
								<td>
									<!--设置当前浏览用户id： 没登陆，游客 uid=999 --> 
									<c:set var="userid"	value="${sessionScope.user==null?999:sessionScope.user.id}"> </c:set>
									 <!-- 锚点传值 --> 
									 <a href="#rshow" title="灌水" data-toggle="modal"
									   id="myp" data-backdrop="static"
									    onclick="rshow(${article.id},${userid},${article.user.id});">
										<i class="icon-eye-open"></i>
								</a> <!-- 是本人贴可以删除和修改 --> <c:if
										test="${sessionScope.user.id!=null && sessionScope.user.id==article.user.id }">
										<a href="ArticleControl?action=del&rootid=0&id=${article.id}"
											title="删除本帖"> <i class="icon-trash"> </i>
										</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<%-- 只能用指令jsp,不能用动作jsp;为了能互相共享参数 --%>
		<%@include file="page.jsp"%>

	</div>


</div>