<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	function changePost(obj) {
	    //获取depid的值
		var depid=obj.value;
		//兼容浏览器并获取ajax核心对象
		var xmlhttp;
        if (window.XMLHttpRequest){
            xmlhttp=new XMLHttpRequest();
        }else{
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function(){
            if(xmlhttp.readyState==4&&xmlhttp.status==200){
                var jsonData=xmlhttp.responseText;
				var postSelect=document.getElementById("postSelectId");
				var jsonArray=eval("("+jsonData+")");
				postSelect.innerHTML="";
				for(var i=0;i<jsonArray.length;i++){
				    var jsonobj=jsonArray[i];//遍历每个json对象
				    var postName=jsonobj.postname;
				    var postId=jsonobj.postid;
				    postSelect.innerHTML+="<option value='"+postId+"'>"+postName+"</option>";
				}
			}
		}
		var url="${pageContext.request.contextPath}/post/findAllPosts?depid="+depid;
		xmlhttp.open("GET",url);
		xmlhttp.send(null);
    }

</script>
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
    <td width="39%" align="left">[员工管理]</td>
   
    <td width="57%"align="right">
    	<%--高级查询 --%>
		<a href="javascript:void(0)" onclick="javascript:document.forms[0].submit()"><img src="${pageContext.request.contextPath}/images/button/gaojichaxun.gif" /></a>
    	<%--员工注入 --%>
	  	<a href="${pageContext.request.contextPath}/staff/toAddStaff">
	  		<img src="${pageContext.request.contextPath}/images/button/tianjia.gif" />
	  	</a>
      
    </td>
    <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
  </tr>
</table>

<!-- 查询条件：马上查询 -->
<form id="conditionFormId" action="${pageContext.request.contextPath}/staff/findStaffInfosByConditon" method="post">
	<table width="88%" border="0" style="margin: 20px;" >
	  <tr>
	    <td width="80px">部门：</td>
	    <td width="200px">
	    	
	    	<select name="crmPost.crmDepartment.depid" onchange="changePost(this)">
			    <option value="">--请选择部门--</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.depid}" >${dept.depname}</option>
				</c:forEach>
			   <%-- <option value="2c9091c14c78e58b014c78e67de10001">java学院</option>
			    <option value="2c9091c14c78e58b014c78e68ded0002">咨询部</option>--%>
			</select>

	    </td>
	    <td width="80px" >职务：</td>
	    <td width="200px" >
	    	
	    	<select name="crmPost.postid" id="postSelectId">
			    <option value="">--请选择职务--</option>


			    <%--<option value="2c9091c14c78e58b014c78e6b34a0003">总监</option>
			    <option value="2c9091c14c78e58b014c78e6d4510004">讲师</option>
			    <option value="2c9091c14c78e58b014c78e6f2340005">主管</option>--%>
			</select>

	    </td>
	    <td width="80px">姓名：</td>
	    <td width="200px" ><input type="text" name="staffname" size="12" /></td>
	    <td ></td>
	  </tr>
	</table>
</form>


<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
  <tr>
    <td ><img src="${pageContext.request.contextPath}/images/result.gif"/></td>
  </tr>
</table>

<table width="100%" border="1" >
  <tr class="henglan" style="font-weight:bold;">
    <td width="10%" align="center">员工姓名</td>
    <td width="6%" align="center">性别</td>
    <td width="12%" align="center">入职时间</td>
    <td width="15%" align="center">所属部门</td>
    <td width="10%" align="center">职务</td>
    <td width="10%" align="center">编辑</td>
  </tr>
  
    <c:forEach items="${pageUtil.items}" var="crmStaff">
	  <tr class="tabtd1"> 
	    <td align="center">${crmStaff.staffname}</td>
	    <td align="center">${crmStaff.gender}</td>
	    <td align="center">${crmStaff.ondutydate}</td>
	    <td align="center">${crmStaff.crmPost.crmDepartment.depname}</td>
	    <td align="center">${crmStaff.crmPost.postname}</td>
	  	<td width="7%" align="center">
	  		
	  		<a href="${pageContext.request.contextPath}/staff/toEditStaff?staffId=${crmStaff.staffid}"><img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img" /></a>
	  	</td>
	  	
	  </tr>
	</c:forEach>
	  <%--<tr class="tabtd2">
	    <td align="center">肉丝</td>
	    <td align="center">女</td>
	    <td align="center">2017-04-16</td>
	    <td align="center">咨询部</td>
	    <td align="center">主管</td>
	  	<td width="7%" align="center">
	  		
	  		<a href="${pageContext.request.contextPath}/pages/staff/editStaff.jsp"><img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img" /></a>	
	  	</td>
	  </tr>--%>
</table>



<table border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td align="right">
    	<span>第${pageUtil.currentPage}/${pageUtil.totalPage}页</span>
        <span>
        	<a href="${pageContext.request.contextPath}/staff/listStaff?currentPage=1">[首页]</a>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/staff/listStaff?currentPage=${pageUtil.currentPage==1?1:pageUtil.currentPage-1}">[上一页]</a>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/staff/listStaff?currentPage=${pageUtil.currentPage==pageUtil.totalPage?pageUtil.totalPage:pageUtil.currentPage+1}">[下一页]</a>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/staff/listStaff?currentPage=${pageUtil.totalPage}">[尾页]</a>
        </span>
    </td>
  </tr>
</table>

</body>
</html>
