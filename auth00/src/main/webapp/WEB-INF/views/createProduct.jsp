<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .form-container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="number"], input[type="url"], input[type="decimal"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #5cb85c;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #4cae4c;
        }
        .alert {
            margin-top: 20px;
            color: #fff;
            padding: 10px;
            display: none;
        }
        .alert-success {
            background-color: #5cb85c;
        }
        .alert-error {
            background-color: #d9534f;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h1>Create Product</h1>

    <div class="form-group">
        <label for="productName">Product Name</label>
        <input type="text" id="productName" placeholder="Enter product name" required>
    </div>

    <div class="form-group">
        <label for="description">Product Description</label>
        <input type="text" id="description" placeholder="Enter product description" required>
    </div>

    <div class="form-group">
        <label for="skuCode">SKU Code</label>
        <input type="text" id="skuCode" placeholder="Enter SKU code" required>
    </div>

    <div class="form-group">
        <label for="price">Price</label>
        <input type="number" id="price" placeholder="Enter price" step="0.01" required>
    </div>

    <div class="form-group">
        <label for="discountPrice">Discount Price</label>
        <input type="number" id="discountPrice" placeholder="Enter discount price" step="0.01" required>
    </div>

    <div class="form-group">
        <label for="quantity">Quantity</label>
        <input type="number" id="quantity" placeholder="Enter quantity" required>
    </div>

    <div class="form-group">
        <label for="imageUrl">Image URL</label>
        <input type="url" id="imageUrl" placeholder="Enter product image URL" required>
    </div>

    <div class="form-group">
        <label for="categoryId">Category ID</label>
        <input type="number" id="categoryId" placeholder="Enter category ID" required>
    </div>

    <div class="form-group">
        <label for="userId">Seller User ID</label>
        <input type="text" id="userId" value="${profile.sub.length() > 6 ? profile.sub.substring(6) : profile.sub}" required>
    </div>

    <button type="button" onclick="createProduct()">Create Product</button>

    <div id="alertSuccess" class="alert alert-success">Product created successfully!</div>
    <div id="alertError" class="alert alert-error">Failed to create product. Please try again.</div>
</div>

<script>
// Function to create a product
function createProduct() {
    const productName = document.getElementById('productName').value;
    const description = document.getElementById('description').value;
    const skuCode = document.getElementById('skuCode').value;
    const price = document.getElementById('price').value;
    const discountPrice = document.getElementById('discountPrice').value;
    const quantity = document.getElementById('quantity').value;
    const imageUrl = document.getElementById('imageUrl').value;
    const categoryId = document.getElementById('categoryId').value;
    const userId = document.getElementById('userId').value;

    const productData = {
        productName: productName,
        description: description,
        skuCode: skuCode,
        price: parseFloat(price),
        discountPrice: parseFloat(discountPrice),
        quantity: parseInt(quantity),
        imageUrl: imageUrl,
        categoryId: parseInt(categoryId),
        userId: userId
    };

    fetch('http://localhost:8085/api/products', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(productData),
    })
    .then(response => {
        if (response.status === 201) {
            document.getElementById('alertSuccess').style.display = 'block';
            document.getElementById('alertError').style.display = 'none';
        } else {
            document.getElementById('alertSuccess').style.display = 'none';
            document.getElementById('alertError').style.display = 'block';
        }
    })
    .catch(error => {
        document.getElementById('alertSuccess').style.display = 'none';
        document.getElementById('alertError').style.display = 'block';
        console.error('Error creating product:', error);
    });
}
</script>

</body>
</html>
