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
    <title>Update User</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>

    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center">Cập nhật User</h2>
    <form method="post">
        <table class="table table-striped text-center">
            <thead>
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Email</th>
                <th>Quốc tịch</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><input type="text" placeholder="Nhập id" name="id" value="${userOld.id}"></td>
                <td><input type="text" placeholder="Nhập tên" name="name" value="${userOld.name}"></td>
                <td><input type="text" placeholder="Nhập Email" name="email" value="${userOld.email}"></td>
                <td><input type="text" placeholder="Nhập quốc tịch" name="country" value="${userOld.country}"></td>
            </tr>
            <tr>
                <td>
                    <button type="submit" class="btn btn-warning">Update</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

</body>
</html>
