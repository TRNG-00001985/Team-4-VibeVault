<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Product</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>Update Product</h2>
    <form action="${pageContext.request.contextPath}/product/update" method="post">
        <input type="hidden" id="id" name="id" value="${product.id}">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${product.name}" required><br>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" value="${product.description}" required><br>
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" value="${product.price}" required><br>
        <button type="submit">Update Product</button>
    </form>
</body>
</html>