<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
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

<body class="emp_body">
<table border="0" cellspacing="0" cellpadding="0" width="100%">
  <tr>
    <td class="topg"></td>
  </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0"  class="wukuang"width="100%">
  <tr>
    <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
    <td width="44%" align="left">[员工管理]</td>

    <td width="52%"align="right">
    	<!-- 提交表单 -->
       <a href="javascript:void(0)" onclick="document.forms[0].submit()">
       	<img src="${pageContext.request.contextPath}/images/button/save.gif" />
       </a>
       <!-- 执行js，进行返回 -->
       <a href="javascript:void(0)" onclick="window.history.go(-1)"><img src="${pageContext.request.contextPath}/images/button/tuihui.gif" /></a>

    </td>
    <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
  </tr>
</table>

<form action="${pageContext.request.contextPath}/staff/addStaff" method="post">
	<table width="88%" border="0" class="emp_table" style="width:80%;">
	 <tr>
	    <td>登录名：</td>
	    <td><input type="text" name="loginname" /> </td>
	    <td>密码：</td>
	    <td><input type="password" name="loginpwd" /> </td>
	  </tr>
	 <tr>
	    <td>姓名：</td>
	    <td><input type="text" name="staffname" value="" id="staffAction_add_staffName"/> </td>
	    <td>性别：</td>
	    <td><input type="radio" name="gender" checked="checked" value="男"/>男
	    	<input type="radio" name="gender"  value="女"/>女
		</td>
	  </tr>
	 <tr>
	    <td width="10%">所属部门：</td>
	    <td width="20%">
	    	<select name="crmPost.crmDepartment.depid"onchange="changePost(this)">
			    <option value="">----请--选--择----</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.depid}">${dept.depname}</option>
				</c:forEach>
				<%--<option value="2c9091c14c78e58b014c78e67de10001">java学院</option>
                <option value="2c9091c14c78e58b014c78e68ded0002">咨询部</option>--%>
			</select>

	    </td>
	    <td width="8%">职务：</td>
	    <td width="62%">
	    	<select id="postSelectId" name="crmPost.postid">
	    		<%--<option>----请--选--择----</option>--%>
	    	</select>
	    </td>
	  </tr>
	   <tr>
	    <td width="10%">入职时间：</td>
	    <td width="20%">
	    	<%--<input type="text" name="ondutydate" value="" readonly="readonly"  onfocus="c.showMoreDay=true; c.show(this);" />--%>
                <input type="date" name="ondutydate" value=""/>
        </td>
	    <td width="8%"></td>
	    <td width="62%"></td>
	  </tr>
	</table>
</form>
</body>
</html>
