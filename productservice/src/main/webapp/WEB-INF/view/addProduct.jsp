<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(to right, #e3f2fd, #81d4fa); /* Gradient background */
            font-family: Arial, sans-serif;
        }
        .navbar {
            background-color: #0288d1; /* Darker sky blue for the navbar */
        }
        .navbar-brand {
            color: white !important; /* White text color for the brand */
        }
        .navbar-nav .nav-link {
            color: white !important; /* White text color for navbar links */
        }
        .navbar-nav .nav-link:hover {
            color: #b3e5fc !important; /* Light sky blue on hover */
        }
        .container {
            margin-top: 30px;
            padding: 30px;
            border-radius: 20px; /* Rounded corners for the container */
            background: rgba(255, 255, 255, 0.9); /* Semi-transparent white background */
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* Larger shadow for a floating effect */
        }
        .form-group label {
            font-weight: bold;
        }
        .btn-custom {
            background-color: #0288d1;
            border: none;
            border-radius: 20px;
            padding: 10px 20px;
            color: white;
        }
        .btn-custom:hover {
            background-color: #01579b;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="#">RevShop SellerDashboard</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="SellerdashBoard.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a href="/view-all-products" class="nav-link">View Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/addProduct">Add Product</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/Sellerorders">Orders</a>
            </li>
            <li class="nav-item">
                <a href="SellerCategories?action=Categories" class="nav-link">Categories</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="SellerProfile?email=${sessionScope.email}">Profile</a>                
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0 ml-auto" action="Sellersearch.jsp" method="get">
            <input class="form-control mr-sm-2" type="search" placeholder="Search Products" aria-label="Search" name="query">
            <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>

<div class="container">
    <h2 class="text-center">Add a New Product</h2>
    <form action="${pageContext.request.contextPath}/product/add" method="POST">
        <div class="form-group">
            <label for="name">Product Name:</label>
            <input type="text" class="form-control" id="name" name="name" required placeholder="Product Name">
        </div>
        <div class="form-group">
            <label for="description">Product Description:</label>
            <textarea class="form-control" id="description" name="description" required placeholder="Product Description"></textarea>
        </div>
        <div class="form-group">
            <label for="price">Product Price:</label>
            <input type="number" class="form-control" id="price" name="price" step="0.01" required placeholder="Product Price">
        </div>
        <div class="form-group">
            <label for="userId">User ID:</label>
            <input type="number" class="form-control" id="userId" name="userId" required placeholder="User ID">
        </div>
        <button type="submit" class="btn btn-custom btn-block">Add Product</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
