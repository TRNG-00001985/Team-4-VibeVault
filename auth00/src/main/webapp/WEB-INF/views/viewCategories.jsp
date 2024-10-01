<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Categories & Products</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #d0e7ff; /* Same light blue background as the products page */
            font-family: 'Arial', sans-serif;
        }
        .container {
            margin-top: 50px;
        }
        h2, h3 {
            text-align: center;
            color: #343a40;
        }
        .category-table, .product-table {
            margin-top: 20px;
        }
        th, td {
            text-align: center;
            padding: 10px;
            font-size: 1.1em;
        }
        .loading, .text-danger {
            text-align: center;
            font-style: italic;
        }
        .category-link {
            color: #007bff;
            cursor: pointer;
            text-decoration: underline;
        }
        tr:hover td {
            background-color: #e9ecef;
        }
        .table-bordered th, .table-bordered td {
            border-color: #dee2e6;
        }
       /* Normal button style (green by default) */
.category-link {
    background-color: #28a745; /* Green by default */
    color: white;
    padding: 8px 16px;
    border-radius: 4px;
    text-decoration: none;
    font-weight: bold;
    display: inline-block;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

/* Blinking effect on hover */
.category-link:hover {
    animation: blink-blue 1s infinite;
}

/* Blue blink animation */
@keyframes blink-blue {
    0% { background-color: #28a745; } /* Green */
    50% { background-color: #007bff; } /* Blue */
    100% { background-color: #28a745; } /* Green */
}
    </style>
</head>
<body>
    <div class="container">
        <h2>Categories List</h2>
        <table class="table table-bordered table-hover category-table">
            <thead class="thead-dark">
                <tr>
                    <th>Category Name</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody id="categoryTableBody">
                <tr>
                    <td colspan="2" class="loading">Loading categories...</td>
                </tr>
            </tbody>
        </table>

        <h3>Products List</h3>
        <table class="table table-bordered table-hover product-table">
            <thead class="thead-dark">
                <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody id="productTableBody">
                <tr>
                    <td colspan="3" class="loading">Select a category to view products</td>
                </tr>
            </tbody>
        </table>
    </div>

    <script>
        // Function to fetch and display categories
        function fetchCategories() {
            fetch('http://localhost:8085/api/categories')  // Adjust the URL based on your backend setup
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.json();
                })
                .then(data => {
                    const tableBody = document.getElementById('categoryTableBody');
                    tableBody.innerHTML = ''; // Clear the loading message

                    if (data.length === 0) {
                        tableBody.innerHTML = '<tr><td colspan="2" class="text-center">No categories available</td></tr>';
                    } else {
                        data.forEach(category => {
                            const row = `
                            	<tr style="background-color: #f9f9f9; border-bottom: 1px solid #ddd;">
                            	<td style="padding: 10px;">
                                <span class="category-link" onclick="fetchProductsByCategory(${category.categoryId})" 
                                      style="cursor: pointer; background-color: #007bff; color: white; padding: 8px 16px; border-radius: 4px; text-decoration: none; font-weight: bold; display: inline-block;">
                                    \${category.categoryName}
                                </span>
                            </td>
                                <td style="padding: 10px;">
                                    \${category.description}
                                </td>
                            </tr>
                            `;
                            tableBody.innerHTML += row;
                        });
                    }
                })
                .catch(error => {
                    console.error('Error fetching categories:', error);
                    const tableBody = document.getElementById('categoryTableBody');
                    tableBody.innerHTML = '<tr><td colspan="2" class="text-center text-danger">Error loading categories</td></tr>';
                });
        }

        // Function to fetch and display products based on category ID
        function fetchProductsByCategory(categoryId) {
            fetch(`http://localhost:8085/api/products/byCategory/${categoryId}`)  // Adjust the URL based on your backend setup
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.json();
                })
                .then(data => {
                    const tableBody = document.getElementById('productTableBody');
                    tableBody.innerHTML = ''; // Clear the loading message

                    if (data.length === 0) {
                        tableBody.innerHTML = '<tr><td colspan="3" class="text-center">No products available for this category</td></tr>';
                    } else {
                        data.forEach(product => {
                            const row = `
                                <tr>
                                    <td>${product.productName}</td>
                                    <td>${product.price}</td>
                                    <td>${product.description}</td>
                                </tr>
                            `;
                            tableBody.innerHTML += row;
                        });
                    }
                })
                .catch(error => {
                    console.error('Error fetching products:', error);
                    const tableBody = document.getElementById('productTableBody');
                    tableBody.innerHTML = '<tr><td colspan="3" class="text-center text-danger">Error loading products</td></tr>';
                });
        }

        // Call the function to load categories on page load
        window.onload = fetchCategories;
    </script>
</body>
</html>
