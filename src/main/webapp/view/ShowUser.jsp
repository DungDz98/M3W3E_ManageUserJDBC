<%--
  Created by IntelliJ IDEA.
  User: dungn
  Date: 7/31/2021
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Menu</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        #addBtn {
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center">List Users</h2>
    <p>Vui lòng tạo User tại đây: <a id="addBtn" href="/user?action=create" class="btn btn-success">Create
        thêm</a></p>
    <div style="display: inline-block">
        <p>Hoặc thử tìm kiếm tại đây:
        <form action="/user?action=findByName" method="get">
            <input type="text" placeholder="Search" name="fName">
            <input type="text" hidden name="action" value="findByName">
            <button class="btn btn-info ml-1" type="submit">Search</button>
        </form>
    </div>
    </p>
    <table class="table table-striped text-center">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Email</th>
            <th>Quốc tịch</th>
            <th colspan="2">Option</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listUser}" var="u">
            <tr>
                <td>${u.id}</td>
                <td>${u.name}</td>
                <td>${u.email}</td>
                <td>${u.country}</td>
                <td><a href="/user?action=delete&id=${u.id}" class="btn btn-danger">Delete</a></td>
                <td><a href="/user?action=update&id=${u.id}" class="btn btn-warning">Update</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>