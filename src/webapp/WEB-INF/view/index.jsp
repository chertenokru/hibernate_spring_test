<%--
  Created by IntelliJ IDEA.
  User: 13th
  Date: 10.10.2018
  Time: 0:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />
    <title>Main</title>
</head>
<body>
<div>
<h1>Меню</h1>
    <div>
        <ul>
            <li><a href="/student/list">Список студентов</a> </li>
            <li><a href="/student/list?sortCourse=true">Список студентов с сортировкой по числу курсов</a> </li>
            <li><a href="/course/list">Список курсов</a> </li>
        </ul>
    </div>
</div>
</body>
</html>
