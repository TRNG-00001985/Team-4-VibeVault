<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: lightblue; /* Use the thick sky blue color as preferred */
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Product List</h1>
        
        <div class="mb-3">
            <a href="addProduct.jsp" class="btn btn-primary">Add New Product</a>
        </div>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>User ID</th>
                    <th>Category ID</th>
                    <th>Product Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Discount Price</th>
                    <th>Quantity</th>
                    <th>Image URL</th>
                    <th>Review Count</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.productId}</td>
                        <td>${product.userId}</td>
                        <td>${product.categoryId}</td>
                        <td>${product.productName}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>${product.discountPrice}</td>
                        <td>${product.quantity}</td>
                        <td><img src="${product.imageUrl}" alt="${product.productName}" style="width: 100px; height: auto;"></td>
                        <td>${product.reviewCount}</td>
                        <td>
                            <a href="updateProduct.jsp?id=${product.productId}" class="btn btn-warning btn-sm">Update</a>
                            <form action="/api/products/${product.productId}" method="post" style="display:inline;">
                                <input type="hidden" name="_method" value="delete">
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this product?');">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
