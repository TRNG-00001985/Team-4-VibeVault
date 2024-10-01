<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            /* Option 1: Light blue background color */
            background-color: #d0e7ff; /* Light blue background */

            /* Option 2: Background image */
            /* background-image: url('path/to/your-background-image.jpg'); */ 
            /* background-size: cover;  Makes sure the image covers the whole page */
            /* background-position: center center; Aligns the image to the center */
            /* background-attachment: fixed; Fixes the background when scrolling */
        }
        .product-card {
            margin: 10px;
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #fff;
            transition: transform 0.3s;
        }
        .product-card:hover {
            transform: translateY(-5px); /* Lift the card slightly on hover */
        }
        .product-image {
            width: 100%;
            height: 200px; /* Fixed height */
            object-fit: cover; /* Ensures the image covers the container without stretching */
            border-radius: 10px;
        }
        .product-card h5 {
            font-size: 1.2rem;
            margin: 10px 0;
            color: #343a40;
        }
        .product-price {
            color: #28a745;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .product-description {
            font-size: 0.9rem;
            color: #6c757d;
            margin-bottom: 10px;
        }
        .btn-primary, .btn-success {
            width: 100%; /* Full-width buttons */
        }
        /* Ensure 4 products per row on larger screens */
        .col-lg-3 {
            flex: 0 0 25%;
            max-width: 25%;
        }
        @media (max-width: 992px) {
            .col-md-4 {
                flex: 0 0 33.33%;
                max-width: 33.33%; /* 3 products per row on medium screens */
            }
        }
        @media (max-width: 768px) {
            .col-sm-6 {
                flex: 0 0 50%;
                max-width: 50%; /* 2 products per row on small screens */
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h2 class="my-4 text-center">View All Products</h2>

    <div class="mb-4 input-group">
        <input type="text" id="searchInput" class="form-control" placeholder="Search for products...">
        <div class="input-group-append">
            <button class="btn btn-primary" onclick="searchProducts()">Search</button>
        </div>
    </div>

    <div id="productsList" class="row">
        <!-- Dynamic product cards will be added here -->
    </div>
</div>

<script>
    // Fetch products automatically on page load
    window.onload = function() {
        fetchProducts();
    };

    function fetchProducts() {
        fetch('http://localhost:9000/api/products')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.json();
            })
            .then(products => {
                window.productsData = products; // Store fetched products for searching
                renderProducts(products);
            })
            .catch(error => console.error('Error fetching products:', error));
    }

    function renderProducts(products) {
        const productsContainer = document.querySelector('#productsList');
        productsContainer.innerHTML = ''; // Clear previous products
        products.forEach(product => {
            const productCard = document.createElement('div');
            productCard.classList.add('col-lg-2', 'col-md-2', 'col-sm-6', 'product-card'); 
            productCard.innerHTML = `
                <img src="\${product.imageUrl}" alt="${product.productName}" class="product-image" onerror="this.onerror=null; this.src='path/to/placeholder-image.jpg';">
                <h5>\${product.productName}</h5>
                <div class="product-price">&#8377;\${product.price.toFixed(2)}</div>
                <div class="product-description">\${product.description}</div>
                <!--  <div>SKU: \${product.skuCode}</div> 
                <div>Quantity: \${product.quantity}</div> -->
                <div>Discount Price: \${product.discountPrice != null ? '₹' + product.discountPrice.toFixed(2) : 'N/A'}</div>
                <button class="btn btn-primary my-2" onclick="addToCart(${product.productId})">Add to Cart</button>
                <button class="btn btn-success my-2" onclick="addToCart(${product.productId})">Buy Now</button>
            `;
            productsContainer.appendChild(productCard);
        });
    }

    function searchProducts() {
        const searchInput = document.getElementById('searchInput').value.toLowerCase();
        const filteredProducts = window.productsData.filter(product =>
            product.productName.toLowerCase().includes(searchInput)
        );
        renderProducts(filteredProducts);
    }

    function addToCart(productId) {
        alert(`Product ID ${productId} added to cart!`);
    }

    function buyNow(productId) {
        window.location.href = `http://localhost/jsp/BuyNow.jsp?productId=${productId}`;
    }
</script>

</body>
</html>
