<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="hdxian.servlet.domain.member.Member" %>
<%@ page import="hdxian.servlet.domain.member.MemberRepository" %>

<%
    MemberRepository repo = MemberRepository.getInstance();

    System.out.println("save.jsp");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    System.out.println("member = " + member);
    repo.save(member);

%>

<html>
<head>
 <meta charset="UTF-8">
</head>
<body>
성공
<ul>
 <li>id=<%=member.getId()%></li>
 <li>username=<%=member.getUsername()%></li>
 <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
