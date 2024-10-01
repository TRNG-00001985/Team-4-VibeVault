<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VIBEVAULT Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        @keyframes gradientBG {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        @keyframes pulse {
            0% { transform: scale(1); }
            50% { transform: scale(1.05); }
            100% { transform: scale(1); }
        }

        @keyframes slideIn {
            from { transform: translateY(-50px); opacity: 0; }
            to { transform: translateY(0); opacity: 1; }
        }

        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
            background-size: 400% 400%;
            animation: gradientBG 15s ease infinite;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        body.dark {
            background: #121212;
            color: #ffffff;
        }

        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: rgba(26, 35, 126, 0.9);
            padding: 15px 20px;
            color: white;
            box-shadow: 0 2px 10px rgba(0,0,0,0.2);
        }

        .navbar h1 {
            margin: 0;
            font-size: 28px;
            font-weight: bold;
            animation: pulse 2s infinite ease-in-out;
        }

        .company-name {
            font-size: 32px;
            font-weight: bold;
            background: linear-gradient(45deg, #FF8E53, #FE6B8B);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            animation: slideIn 1s ease-out;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
            transition: all 0.3s ease;
            position: relative;
            font-weight: 500;
        }

        .navbar a::after {
            content: '';
            position: absolute;
            width: 100%;
            height: 2px;
            bottom: -5px;
            left: 0;
            background-color: #64b5f6;
            transform: scaleX(0);
            transition: transform 0.3s ease;
        }

        .navbar a:hover::after {
            transform: scaleX(1);
        }

        .dashboard-container {
            text-align: center;
            margin: 40px auto;
            max-width: 800px;
            padding: 30px;
            animation: slideIn 0.5s ease-out;
            flex-grow: 1;
        }

        .dashboard-container h1 {
            color: white;
            margin-bottom: 30px;
            font-size: 32px;
            font-weight: bold;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
        }

        .nav-links {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-top: 30px;
        }

        .nav-links a {
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 15px 0;
            font-size: 18px;
            background-color: rgba(26, 35, 126, 0.8);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            position: relative;
            overflow: hidden;
        }

        .nav-links a::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(
                120deg,
                transparent,
                rgba(255, 255, 255, 0.3),
                transparent
            );
            transition: all 0.5s;
        }

        .nav-links a:hover::before {
            left: 100%;
        }

        .nav-links a:hover {
            background-color: rgba(48, 63, 159, 0.9);
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0,0,0,0.2);
        }

        .logout-section {
            margin-top: 40px;
        }

        .logout-section button {
            padding: 12px 25px;
            background-color: rgba(244, 67, 54, 0.8);
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: all 0.3s ease;
            font-size: 16px;
            font-weight: bold;
            text-transform: uppercase;
            position: relative;
            overflow: hidden;
        }

        .logout-section button::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(
                120deg,
                transparent,
                rgba(255, 255, 255, 0.3),
                transparent
            );
            transition: all 0.5s;
        }

        .logout-section button:hover::before {
            left: 100%;
        }

        .logout-section button:hover {
            background-color: rgba(211, 47, 47, 0.9);
            transform: translateY(-3px);
            box-shadow: 0 5px 15px rgba(244,67,54,0.4);
        }

        .theme-toggle {
            background-color: rgba(255, 255, 255, 0.3);
            border: none;
            border-radius: 50%; /* Circular shape */
            width: 40px; /* Size of the button */
            height: 40px; /* Size of the button */
            cursor: pointer;
            margin-left: 10px; /* Space between Home and toggle button */
            transition: background-color 0.3s ease;
        }

        .theme-toggle:hover {
            background-color: rgba(255, 255, 255, 0.5); /* Slightly lighter on hover */
        }

        @media (max-width: 600px) {
            .navbar {
                flex-direction: column;
                text-align: center;
            }

            .navbar a {
                margin: 10px 0 0 0;
            }

            .dashboard-container {
                padding: 20px;
            }

            .nav-links {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>

<div class="navbar">
    <h1 class="company-name">VIBEVAULT</h1>
    <div style="display: flex; align-items: center;">
        <button id="theme-toggle" class="theme-toggle">
            <i class="fas fa-sun"></i>
        </button>
        <a href="/jsp/home2">Home</a>
        <a href="/jsp/profile">Profile</a>
        <a href="/jsp/help">Help</a>
    </div>
</div>

<div class="dashboard-container">
    <h1>Welcome to Your Dashboard</h1>

    <div class="nav-links">
        <a href="/jsp/createProduct">Add Products</a>
        <a href="/jsp/ProductManager">View Products</a>
        <a href="/jsp/viewOrders">Check Orders</a>
        <a href="/jsp/profile">View Profile</a>
    </div>

    <div class="logout-section">
        <button onclick="window.location.href='/logout'">Logout</button>
    </div>
</div>

<script>
    const toggleButton = document.getElementById('theme-toggle');
    const body = document.body;

    toggleButton.addEventListener('click', () => {
        body.classList.toggle('dark');
        // Change the icon based on the theme
        if (body.classList.contains('dark')) {
            toggleButton.innerHTML = '<i class="fas fa-moon"></i>'; // Moon icon for dark mode
        } else {
            toggleButton.innerHTML = '<i class="fas fa-sun"></i>'; // Sun icon for light mode
        }
    });
</script>

</body>
</html>