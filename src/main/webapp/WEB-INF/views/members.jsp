<%@ page contentType="text/html;charset=utf-8" language="java" %>

<!-- for c:forEach -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
 <meta charset="UTF-8">
 <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
 <thead>
 <th>id</th>
 <th>username</th>
 <th>age</th>
 </thead>
<tbody>

<!-- jsp loop func -->
<c:forEach var="item" items="${members}">
    <tr>
        <td>id=${item.id}</td>
        <td>username=${item.username}</td>
        <td>age=${item.age}</td>
    </tr>
</c:forEach>

</tbody>
</table>
</body>
</html>