<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />

    <title>Информация о курсе ${course.description}</title>

</head>
<body>

<h1>Информация о курсе ${course.description}</h1>

На курсе обучаются студенты :
<table>
    <th>Id</th>
    <th>Имя</th>
    <th>Полная информация</th>
    <c:forEach var="student" items="${course.students}">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td><a href="/student/detail/${student.id}">Полная информация</a></td>
        </tr>
    </c:forEach>
</table>
<p> <a href="/">Назад</a></p>
</body>
</html>
