<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #e6f7ff; /* Soft light blue background */
            margin: 0;
            padding: 0;
            transition: background-color 0.3s, color 0.3s; /* Transition for smooth theme change */
        }

        .dark-theme {
            background-color: #1a1a1a; /* Dark background */
            color: #ffffff; /* Light text */
        }

        .profile-container {
            max-width: 800px;
            margin: 50px auto;
            display: flex;
            align-items: center;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
            background-color: #ffffff; /* White background for the main container */
            transition: background-color 0.3s, box-shadow 0.3s; /* Transition for smooth theme change */
        }

        .dark-theme .profile-container {
            background-color: #2b2b2b; /* Darker background for the container */
            box-shadow: 0 4px 10px rgba(255, 255, 255, 0.1); /* Lighter shadow for dark theme */
        }

        .profile-picture {
            flex: 1; /* Takes one part of the flexbox */
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .profile-picture img {
            max-width: 150px;
            height: auto;
            border-radius: 50%;
            border: 3px solid #0056b3; /* Adding border to profile picture */
            transition: transform 0.3s ease;
        }

        .profile-picture img:hover {
            transform: scale(1.05); /* Scale effect on hover */
        }

        .profile-details {
            flex: 2; /* Takes two parts of the flexbox */
            padding: 20px;
            border-radius: 10px;
            background: linear-gradient(135deg, #a4d4e1 30%, #e2f0f7 100%); /* Gradient background */
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Slight shadow for depth */
            transition: background 0.3s, box-shadow 0.3s; /* Transition for smooth theme change */
        }

        .dark-theme .profile-details {
            background: linear-gradient(135deg, #3a3a3a 30%, #484848 100%); /* Dark gradient background */
            box-shadow: 0 2px 10px rgba(255, 255, 255, 0.1); /* Lighter shadow for dark theme */
        }

        .profile-details h2 {
            color: #0056b3;
            font-size: 32px;
            margin-bottom: 20px;
        }

        .profile-item {
            font-size: 20px;
            margin: 15px 0;
            color: #333;
        }

        .dark-theme .profile-item {
            color: #ddd; /* Light text for dark theme */
        }

        .profile-item strong {
            color: #0056b3;
            font-weight: 600;
        }

        .edit-btn, .logout-btn {
            margin-top: 15px;
            padding: 12px 25px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .edit-btn {
            background-color: #0056b3;
            color: white;
        }

        .edit-btn:hover {
            background-color: #004494;
            transform: translateY(-3px);
        }

        .logout-btn {
            background-color: #ff4d4d;
            color: white;
        }

        .logout-btn:hover {
            background-color: #e60000;
            transform: translateY(-3px);
        }

        .theme-toggle {
            position: absolute;
            top: 20px;
            right: 20px;
            padding: 10px;
            background-color: #fff;
            border: none;
            border-radius: 50%;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
        }

        .theme-toggle:hover {
            background-color: #f0f0f0;
            transform: scale(1.05);
        }
    </style>
</head>
<body>

    <button class="theme-toggle" id="theme-toggle">
        <i class="fa-solid fa-sun"></i> <!-- Sun icon for light mode -->
    </button>

    <div class="profile-container">
        <div class="profile-picture">
            <img src="${profile['picture']}" alt="Profile Picture" />
        </div>
        <div class="profile-details">
            <h2>My Account</h2>
            <p class="profile-item"><strong>Email:</strong> ${profile['email']}</p>
            <p class="profile-item"><strong>Name:</strong> ${profile['nickname']}</p>
            <p class="profile-item"><strong>Role:</strong> ${profile['role']}</p>

<!--             <button class="edit-btn" onclick="window.location.href='/edit-profile'">Edit Profile</button>
 -->            <button class="logout-btn" onclick="window.location.href='/logout'">Logout</button>
        </div>
    </div>

    <script>
        const toggleButton = document.getElementById('theme-toggle');
        const body = document.body;

        toggleButton.addEventListener('click', () => {
            body.classList.toggle('dark-theme');

            // Change the icon based on the theme
            if (body.classList.contains('dark-theme')) {
                toggleButton.innerHTML = '<i class="fa-solid fa-moon"></i>'; // Moon icon for dark mode
            } else {
                toggleButton.innerHTML = '<i class="fa-solid fa-sun"></i>'; // Sun icon for light mode
            }
        });
    </script>

    <!-- Include Font Awesome for icons -->
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</body>
</html>