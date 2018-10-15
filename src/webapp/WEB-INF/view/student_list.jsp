<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список студентов</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />
</head>
<body>
<h1>Список студентов</h1>

<table>
    <th>Id</th>
    <th>Имя</th>
    <th>Кол-во курсов</th>
    <th></th>

    <c:forEach var="course" items="${studentList}">
        <tr>
            <td>${course.id}</td>
            <td>${course.name}</td>
            <td>${course.coursesCount}</td>
            <td><a href="/student/detail/${course.id}">Полная информация</a> </td>
        </tr>
    </c:forEach>
    </table>
<p> <a href="/">Назад</a>
</body>
</html>
