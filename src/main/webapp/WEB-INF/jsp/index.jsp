<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: xuan
  Date: 2018/1/26
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/test.css">
    <%--<script type="text/javascript" src="js/test.js?v=<%=new Date().getTime()%>"></script>--%>
    <%--<script type="text/javascript" src="js/test.js?v=<%=Math.random()%>"></script>--%>
    <title>首页</title>
    <%--<script type="text/javascript">
        document.write("<script type='text/javascript' src='js/test.js?"+new Date().getTime()+"'><\/script>");
    </script>--%>
</head>
<body>
    欢迎！
    <form action="fileUpload.action" method="post" enctype="multipart/form-data">
        <input type="file" name="uploadFile" value="上传">
        <input type="submit" value="提交">
    </form>

</body>
</html>
