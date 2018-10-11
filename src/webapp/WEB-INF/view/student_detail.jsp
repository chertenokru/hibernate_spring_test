<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 13th
  Date: 10.10.2018
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Студент - ${student.name}</title>
</head>
<body>
<h1>Информация о студенте ${student.name}</h1>

Студент обучается на ${student.courses.size()} курсах:
<table>
    <th>Id</th>
    <th>Название</th>
    <th>Кол-во занятий</th>
    <th>Кол-во студентов</th>
    <th>Полная информация</th>
    <c:forEach var="course" items="${student.courses}">
    <tr>
        <td>${course.id}</td>
        <td>${course.description}</td>
        <td>${course.courseLength}</td>
        <td>${course.students.size()}</td>
        <td><a href="/cource/detail/${course.id}">Полная информация</a></td>
    </tr>
    </c:forEach>
</table>

</body>
</html>
