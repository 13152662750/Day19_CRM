<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet" />

</head>

<body >
 <table border="0" cellspacing="0" cellpadding="0" width="100%">
  <tr>
    <td class="topg"></td>
  </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0"  class="wukuang"width="100%">
  <tr>
    <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
    <td width="39%" align="left">[职务管理]</td>
   
    <td width="57%"align="right">
    	<%--添加职务 --%>
       <a href="${pageContext.request.contextPath}/post/toAddPost">
       	<img src="${pageContext.request.contextPath}/images/button/tianjia.gif" />
       </a>
      
    </td>
    <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
  </tr>
</table>
<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
  <tr>
    <td ><img src="${pageContext.request.contextPath}/images/result.gif"/></td>
  </tr>
</table>

<table width="100%" border="1" >
  
  <tr class="henglan" style="font-weight:bold;">
    <td width="6%" align="center">部门名称</td>
    <td width="6%" align="center">职务名称</td>
    <td width="7%" align="center">编辑</td>
  </tr>
  <c:forEach items="${pageUtil.items}" var="crmPost">
  	<tr class="tabtd1">
	    <td align="center">${crmPost.crmDepartment.depname} </td>
	    <td align="center">${crmPost.postname} </td>
	  	<td width="7%" align="center">
	  		<a href="${pageContext.request.contextPath}/post/toUpdatePost?postid=${crmPost.postid}"><img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img" /></a>
	  	</td>
	  </tr>
  </c:forEach>
  	<%--<tr class="tabtd2">
	    <td align="center">java学院 </td>
	    <td align="center">讲师 </td>
	  	<td width="7%" align="center">
	  		<a href="${pageContext.request.contextPath}/pages/post/addOrEditPost.jsp"><img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img" /></a>
	  	</td>
	  </tr>--%>
</table>



<table border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td align="right">
    	<span>第${pageUtil.currentPage}/${pageUtil.totalPage}页</span>
        <span>
        	<a href="${pageContext.request.contextPath}/post/listPost?currentPage=1">[首页]</a>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/post/listPost?currentPage=${pageUtil.currentPage==1?1:pageUtil.currentPage-1}">[上一页]</a>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/post/listPost?currentPage=${pageUtil.currentPage==pageUtil.totalPage?pageUtil.totalPage:pageUtil.currentPage+1}">[下一页]</a>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/post/listPost?currentPage=${pageUtil.totalPage}">[尾页]</a>
        </span>
    </td>
  </tr>
</table>
</body>
</html>
