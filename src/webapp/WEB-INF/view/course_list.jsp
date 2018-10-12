<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />
    <title>Список курсов</title>
</head>
<body>
<h1>Список курсов</h1>

<table>
    <th>Id</th>
    <th>Название</th>
    <th>Кол-во занятий</th>
    <th>Кол-во студентов</th>
    <th></th>

    <c:forEach var="student" items="${coursetList}">
        <tr>
            <td>${student.id}</td>
            <td>${student.description}</td>
            <td>${student.length}</td>
            <td>${student.studentCount}</td>
        </tr>
    </c:forEach>
    </table>
<p> <a href="/">Назад</a></p>
</body>
</html>
