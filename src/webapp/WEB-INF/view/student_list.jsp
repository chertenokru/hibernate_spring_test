<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список студентов</title>
</head>
<body>
<h1>Список студентов</h1>

<table>
    <th>Id</th>
    <th>Имя</th>
    <th>Кол-во курсов</th>
    <th></th>

    <c:forEach var="student" items="${studentList}">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.courses.size()}</td>
            <td><a href="/student/detail/${student.id}">Полная информация</a> </td>
        </tr>
    </c:forEach>
    </table>

</body>
</html>
