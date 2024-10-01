<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>Your Shopping Cart</h2>
        <div id="cartContainer" class="row">
            <!-- Cart items will be dynamically loaded here -->
        </div>
        <div class="mt-3">
            <button class="btn btn-danger" onclick="clearCart()">Clear Cart</button>
            <button class="btn btn-success" onclick="placeOrder()">Place Order</button>
        </div>
    </div>

    <script type="text/javascript">
        // Assume `userId` is available as a session variable or provided globally
        const userId = 'your-user-id'; // Replace this with dynamic userId
        
        $(document).ready(function() {
            loadCart();
        });

        function loadCart() {
            $.ajax({
                url: `http://localhost:8086/api/cart/${userId}`,
                type: 'GET',
                success: function(cart) {
                    displayCartItems(cart.items);
                },
                error: function() {
                    alert("Error loading cart.");
                }
            });
        }

        function displayCartItems(items) {
            const cartContainer = $('#cartContainer');
            cartContainer.empty(); // Clear previous items
            if (items.length === 0) {
                cartContainer.html('<p>Your cart is empty.</p>');
                return;
            }
            items.forEach(item => {
                cartContainer.append(`
                    <div class="col-md-3 mb-4">
                        <div class="card">
                            <img src="${item.product.imageUrl}" class="card-img-top" alt="${item.product.productName}">
                            <div class="card-body">
                                <h5 class="card-title">${item.product.productName}</h5>
                                <p class="card-text">&#8377; ${item.product.price.toFixed(2)}</p>
                                <p>Quantity: ${item.quantity}</p>
                                <button class="btn btn-danger" onclick="removeFromCart(${item.product.productId})">Remove</button>
                            </div>
                        </div>
                    </div>
                `);
            });
        }

        function addToCart(productId, quantity) {
            const itemDto = {
                productId: productId,
                quantity: quantity
            };
            $.ajax({
                url: `http://localhost:8086/api/cart/${userId}/add`,
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(itemDto),
                success: function(updatedCart) {
                    loadCart();
                },
                error: function() {
                    alert("Error adding to cart.");
                }
            });
        }

        function removeFromCart(productId) {
            $.ajax({
                url: `http://localhost:8086/api/cart/${userId}/${productId}/delete`,
                type: 'DELETE',
                success: function(updatedCart) {
                    loadCart();
                },
                error: function() {
                    alert("Error removing item.");
                }
            });
        }

        function clearCart() {
            $.ajax({
                url: `http://localhost:8086/api/cart/${userId}/clear`,
                type: 'DELETE',
                success: function() {
                    loadCart();
                },
                error: function() {
                    alert("Error clearing cart.");
                }
            });
        }

        function placeOrder() {
            const orderRequest = {
                // Add required fields for order request
            };
            $.ajax({
                url: `http://localhost:8086/api/cart/${userId}/placeOrder`,
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(orderRequest),
                success: function(orderResponse) {
                    alert('Order placed successfully!');
                    loadCart(); // Clear the cart after placing order
                },
                error: function() {
                    alert("Error placing order.");
                }
            });
        }
    </script>
</body>
</html>
