<div class="dashboard-container">
    <h1>Welcome to Your Dashboard</h1>
    <p>Manage your account, view products, and check your orders below.</p>

    <div class="dashboard-grid">
        <div class="dashboard-item">
            <h2>Available Products</h2>
            <button onclick="window.location.href='/jsp/viewAllProducts'">View Products</button>
        </div>
        
        <div class="dashboard-item">
            <h2>Categories</h2>
            <button onclick="window.location.href='/jsp/viewCategories'">View Categories</button>
        </div>
        <div class="dashboard-item">
            <h2>Your Cart</h2>
            <button onclick="window.location.href='/jsp/viewCart'">View Cart</button>
        </div>
        <div class="dashboard-item">
            <h2>View Orders</h2>
            <button onclick="window.location.href='/jsp/viewOrders'">Check Orders</button>
        </div>
        <div class="dashboard-item">
            <h2>View Profile</h2>
            <button onclick="window.location.href='/jsp/profile'">View Profile</button>
        </div>
    </div>

    <div class="logout-section">
        <button onclick="window.location.href='/logout'">Logout</button>
    </div>

    <div class="theme-toggle">
        <button id="themeButton" onclick="toggleTheme()">Switch to Light Mode</button>
    </div>
</div>

<style>
	@import url('https://fonts.googleapis.com/css2?family=Rubik:wght@300;400;500&display=swap');

	:root {
	  --primary-color: #6200ea;
	  --secondary-color: #03dac6;
	  --background-color: #121212;
	  --surface-color: #1e1e1e;
	  --on-surface-color: #ffffff;
	  --error-color: #cf6679;
	}

	/* Light mode variables */
	.light-mode {
	  --background-color: #ffffff;
	  --surface-color: #f1f1f1;
	  --on-surface-color: #000000;
	  --error-color: #b00020;
	}

	body {
	  font-family: 'Rubik', sans-serif;
	  background-color: var(--background-color);
	  color: var(--on-surface-color);
	  margin: 0;
	  padding: 0;
	  min-height: 100vh;
	  display: flex;
	  justify-content: center;
	  align-items: center;
	  overflow-x: hidden;
	}

	.dashboard-container {
	  width: 100%;
	  max-width: 1200px;
	  padding: 2rem;
	}

	h1 {
	  font-size: 3rem;
	  font-weight: 500;
	  color: var(--secondary-color);
	  margin-bottom: 1rem;
	  text-align: center;
	  animation: glowingText 2s ease-in-out infinite alternate;
	}

	@keyframes glowingText {
	  from {
	    text-shadow: 0 0 5px var(--secondary-color), 0 0 10px var(--secondary-color);
	  }
	  to {
	    text-shadow: 0 0 10px var(--secondary-color), 0 0 20px var(--secondary-color), 0 0 30px var(--secondary-color);
	  }
	}

	p {
	  font-size: 1rem;
	  color: rgba(255, 255, 255, 0.7);
	  margin-bottom: 3rem;
	  text-align: center;
	  max-width: 600px;
	  margin-left: auto;
	  margin-right: auto;
	  animation: fadeInUp 1s ease-out 0.5s both;
	}

	@keyframes fadeInUp {
	  from {
	    opacity: 0;
	    transform: translateY(20px);
	  }
	  to {
	    opacity: 1;
	    transform: translateY(0);
	  }
	}

	.dashboard-grid {
	  display: grid;
	  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
	  gap: 2rem;
	}

	.dashboard-item {
	  background-color: var(--surface-color);
	  border-radius: 20px;
	  overflow: hidden;
	  transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.55);
	  animation: flipIn 0.6s cubic-bezier(0.68, -0.55, 0.265, 1.55) backwards;
	}

	@keyframes flipIn {
	  0% {
	    opacity: 0;
	    transform: perspective(400px) rotateX(-90deg);
	  }
	  100% {
	    opacity: 1;
	    transform: perspective(400px) rotateX(0deg);
	  }
	}

	.dashboard-item:hover {
	  transform: translateY(-10px) scale(1.03);
	  box-shadow: 0 20px 30px rgba(0, 0, 0, 0.3);
	}

	.dashboard-item h2 {
	  font-size: 1.5rem;
	  font-weight: 500;
	  margin: 0;
	  padding: 1.5rem;
	  background-color: rgba(255, 255, 255, 0.05);
	  position: relative;
	  overflow: hidden;
	}

	.dashboard-item h2::after {
	  content: '';
	  position: absolute;
	  top: 0;
	  left: -100%;
	  width: 100%;
	  height: 100%;
	  background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.2), transparent);
	  transition: 0.5s;
	}

	.dashboard-item:hover h2::after {
	  left: 100%;
	}

	button {
	  width: 100%;
	  padding: 1rem;
	  font-size: 1rem;
	  font-weight: 500;
	  text-transform: uppercase;
	  letter-spacing: 1px;
	  border: none;
	  color: var(--on-surface-color);
	  cursor: pointer;
	  transition: all 0.3s ease;
	  position: relative;
	  overflow: hidden;
	  background-color: transparent;
	  border-radius: 50px;
	}

	button::before {
	  content: '';
	  position: absolute;
	  top: 0;
	  left: -100%;
	  width: 100%;
	  height: 100%;
	  background: linear-gradient(120deg, transparent, rgba(255, 255, 255, 0.3), transparent);
	  transition: all 0.6s;
	}

	button:hover::before {
	  left: 100%;
	}

	button:hover {
	  transform: translateY(-3px);
	  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
	}

	.dashboard-item:nth-child(1) button { background-color: #f44336; }
	.dashboard-item:nth-child(2) button { background-color: #ff9800; }
	.dashboard-item:nth-child(3) button { background-color: #4caf50; }
	.dashboard-item:nth-child(4) button { background-color: #2196f3; }
	.dashboard-item:nth-child(5) button { background-color: #9c27b0; }

	.logout-section {
	  margin-top: 3rem;
	  text-align: center;
	}

	.logout-section button {
	  background-color: var(--error-color);
	  color: var(--background-color);
	  padding: 1rem 2rem;
	  max-width: 200px;
	  display: inline-block;
	  transition: all 0.3s ease;
	}

	.logout-section button:hover {
	  background-color: #ff4081;
	  transform: scale(1.05);
	}

	.dashboard-item:hover button {
	  animation: floatingButton 2s ease-in-out infinite;
	}

	@media (max-width: 768px) {
	  .dashboard-container {
	    padding: 1rem;
	  }

	  h1 {
	    font-size: 2rem;
	  }

	  p {
	    font-size: 0.9rem;
	  }

	  .dashboard-item h2 {
	    font-size: 1.2rem;
	    padding: 1rem;
	  }

	  button {
	    padding: 0.8rem;
	    font-size: 0.9rem;
	  }
	}
</style>

<script>
    function toggleTheme() {
        const body = document.body;
        body.classList.toggle("light-mode");

        const themeButton = document.getElementById("themeButton");
        if (body.classList.contains("light-mode")) {
            themeButton.innerText = "Switch to Dark Mode";
        } else {
            themeButton.innerText = "Switch to Light Mode";
        }
    }
</script>