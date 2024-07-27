<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="hdxian.servlet.domain.member.Member" %> <!-- this is unnecessary if use \$\{\} -->

<html>
<head>
 <meta charset="UTF-8">
</head>
<body>
성공
<!--
<ul>
 <li>id=<%= ((Member)request.getAttribute("member")).getId() %></li>
 <li>username=<%= ((Member)request.getAttribute("member")).getUsername() %></li>
 <li>age=<%= ((Member)request.getAttribute("member")).getAge() %></li>
</ul>
-->
<ul>
 <li>id=${member.id}</li>
 <li>username=${member.username}</li>
 <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
