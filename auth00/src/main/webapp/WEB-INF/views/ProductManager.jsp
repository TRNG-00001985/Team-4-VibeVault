<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4; /* Light background for the page */
        }

        .dashboard-item {
            margin-bottom: 20px;
        }

        .product-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around; /* Center items */
            margin-top: 20px;
        }

        .product-card {
            background-color: #ffffff; /* White background for cards */
            border: 1px solid #dee2e6; /* Light border color */
            border-radius: 5px; /* Rounded corners for cards */
            margin: 10px;
            padding: 10px;
            text-align: center; /* Center text within cards */
            width: 200px; /* Fixed width for cards */
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Subtle shadow */
        }

        .product-image {
            width: 100px; /* Set a fixed width for images */
            height: auto;
            border-radius: 5px; /* Rounded corners for images */
        }

        .product-price {
            font-size: 18px;
            color: #28a745; /* Green color for price */
            margin: 10px 0;
        }

        .product-description {
            font-size: 14px; /* Smaller font for description */
            color: #555; /* Gray color */
            margin: 5px 0;
        }

        .user-id {
            display: none; /* Hide user ID */
        }

        .action-button {
            margin: 5px; /* Add some margin between buttons */
            cursor: pointer; /* Show pointer cursor on hover */
            background-color: #007bff; /* Bootstrap primary color */
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 3px; /* Rounded corners for buttons */
            transition: background-color 0.3s;
        }

        .action-button:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }
    </style>
</head>
<body>

    <div class="dashboard-item">
        <h2>View Products</h2>
    </div>

    <div id="productsList" class="product-container">
        <!-- Dynamic product cards will be added here -->
    </div>

    <script>
        // Fetch products automatically on page load
        window.onload = function() {
            fetchProducts();
        };

        function fetchProducts() {
            console.log('Fetching products...'); // Debugging log
            fetch('http://localhost:9000/api/product')
                .then(response => {
                    console.log('Response status:', response.status); // Debugging log
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.json();
                })
                .then(products => {
                    console.log('Fetched products:', products); // Debugging log
                    const productsContainer = document.querySelector('#productsList');
                    productsContainer.innerHTML = ''; // Clear previous products
                    products.forEach(product => {
                        const productCard = document.createElement('div');
                        productCard.classList.add('product-card');
                        productCard.innerHTML = `
                            <img src=\"${product.imageurl}" alt="${product.name}" class="product-image" onerror="this.onerror=null; this.src='path/to/placeholder-image.jpg';">
                            <h3>\${product.name}</h3>
                            <div class=\"product-price">&#8377;\${product.price.toFixed(2)}</div>
                            <div class=\"product-description">${product.description}</div>
                            <div>SKU: \${product.skuCode}</div>
                            <div class="user-id">User ID: \${product.userId}</div>
                            <button class="action-button" onclick="updateProduct(\${product.id})">Edit</button>
                            <button class="action-button" onclick="deleteProduct(\${product.id})">Delete</button>
                        `;
                        productsContainer.appendChild(productCard);
                    });
                })
                .catch(error => console.error('Error fetching products:', error));
        }

        function updateProduct(productId) {
            console.log('Update product with ID:', productId);
            // Redirect to updateProduct page, passing productId
            window.location.href = `http://localhost/jsp/updateProduct?id=\${productId}`;
        }

        function deleteProduct(productId) {
            console.log('Delete product with ID:', productId);
            if (confirm(`Are you sure you want to delete product ID \${productId}?`)) {
                fetch(`http://localhost:9000/api/product?id=\${productId}`, {
                    method: 'DELETE'
                })
                .then(response => {
                    if (response.ok) {
                        console.log(`Product with ID ${productId} deleted successfully.`);
                        fetchProducts(); // Refresh the product list after deletion
                    } else {
                        console.error('Error deleting product:', response.statusText);
                    }
                })
                .catch(error => console.error('Error deleting product:', error));
            }
        }
    </script>

</body>
</html>
