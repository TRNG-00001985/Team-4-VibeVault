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
            background-color: #f4f4f4;
        }
        .product-card {
            margin: 10px;
            padding: 10px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            background-color: white;
        }
        .product-image {
            width: 100%;
            height: auto;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2 class="my-4">View All Products</h2>

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
        fetch('http://localhost:8085/api/products')
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
            productCard.classList.add('col-md-3', 'product-card'); // Use Bootstrap columns
            productCard.innerHTML = `
                <img src="${product.imageUrl}" alt="${product.productName}" class="product-image" onerror="this.onerror=null; this.src='path/to/placeholder-image.jpg';">
                <h5>${product.productName}</h5>
                <div class="product-price">&#8377;${product.price.toFixed(2)}</div>
                <div class="product-description">${product.description}</div>
                <div>SKU: ${product.skuCode}</div>
                <div>Quantity: ${product.quantity}</div>
                <div>Discount Price: ${product.discountPrice ? `&#8377;${product.discountPrice.toFixed(2)}` : 'N/A'}</div>
                <div>Reviews: ${product.reviewCount}</div>
                <button class="btn btn-primary" onclick="addToCart(${product.productId})">Add to Cart</button>
                <button class="btn btn-success" onclick="buyNow(${product.productId})">Buy Now</button>
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
