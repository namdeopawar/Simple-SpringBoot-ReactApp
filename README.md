# Simple Spring Boot and React Application

This is a full-stack application built with Spring Boot for the backend and React for the frontend. The application allows users to manage a list of users, including creating and viewing user details.

## Features

- User management (create and view users)
- Built with Spring Boot and React
- RESTful API for backend communication
- Responsive design

## Technologies Used

- **Backend**: Spring Boot, Java, PostgreSQL
- **Frontend**: React, Axios, CSS
- **Build Tool**: Maven
- **Web Server**: Nginx

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed on your machine:

- **Java 17 or higher**: [Download Java](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- **Node.js (14.x, 16.x, or 18.x)**: [Download Node.js](https://nodejs.org/)
- **PostgreSQL**: [Download PostgreSQL](https://www.postgresql.org/download/)

### Installation

1. **Clone the Repository**:

   Open your terminal and run the following command to clone the repository:

   ```bash
   git clone https://github.com/namdeopawar/Simple-SpringBoot-ReactApp.git
   cd Simple-SpringBoot-ReactApp
   ```

2. **Set Up the Backend**:

   - Navigate to the backend directory:

     ```bash
     cd backend
     ```

   - **Update Database Configuration**:

     Open the `src/main/resources/application.properties` file and update the database configuration with your PostgreSQL credentials:

     ```properties
     # Database Configuration
     spring.datasource.url=jdbc:postgresql://localhost:5432/testdb
     spring.datasource.username=testuser
     spring.datasource.password=TestPass123
     spring.jpa.hibernate.ddl-auto=update
     server.port=8080
     ```

   - **Run the Spring Boot Application**:

     Make sure you have Maven installed. If not, you can install it by following the instructions [here](https://maven.apache.org/install.html).

     Run the following command to start the Spring Boot application:

     ```bash
     mvn spring-boot:run
     ```

   - **Create Initial User Records** (Optional):

     You can use the provided API to create initial user records. Use the following command to add a user:

     ```bash
     curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d '{"name": "John Doe", "email": "john@example.com"}'
     ```

3. **Set Up the Frontend**:

   - Navigate to the frontend directory:

     ```bash
     cd frontend
     ```

   - **Install Dependencies**:

     Run the following command to install the required Node.js packages:

     ```bash
     npm install
     ```

   - **Build the React Application**:

     After installing the dependencies, build the React application by running:

     ```bash
     npm run build
     ```

   This will create a `build` directory containing the static files for your React application.

4. **Deploy the Frontend Build Files**:

   - Copy the contents of the `build` directory to your web server's root directory (e.g., `/var/www/html`):

     ```bash
     sudo cp -r build/* /var/www/html/
     ```

### Nginx Configuration

To serve your React application and proxy requests to your Spring Boot backend, you need to configure Nginx.

1. **Install Nginx**:

   If you haven't installed Nginx yet, you can do so with the following command:

   ```bash
   sudo apt update
   sudo apt install nginx
   ```

2. **Create Nginx Configuration**:

   Open a new configuration file for your application:

   ```bash
   sudo nano /etc/nginx/sites-available/myapp
   ```

   Add the following configuration:

   ```nginx
   server {
       listen 80;
       server_name your_public_ip;  # Replace with your public IP address

       # Serve the React app
       location / {
           root /var/www/html;  # Path to your React build files
           index index.html index.htm;
           try_files $uri $uri/ /index.html;  # Redirect all requests to index.html
       }

       # Proxy requests to the Spring Boot backend
       location /api/ {
           proxy_pass http://localhost:8080;  # Change this to your Spring Boot backend URL
           proxy_set_header Host $host;
           proxy_set_header X-Real-IP $remote_addr;
           proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
           proxy_set_header X-Forwarded-Proto $scheme;
       }

       # Optional: Handle 404 errors
       error_page 404 /404.html;  # Create a custom 404 page if needed
       location = /404.html {
           internal;
       }
   }
   ```

3. **Enable the Configuration**:

   Create a symbolic link to enable the configuration:

   ```bash
   sudo ln -s /etc/nginx/sites-available/myapp /etc/nginx/sites-enabled/
   ```

4. **Test the Nginx Configuration**:

   Before restarting Nginx, test the configuration for any syntax errors:

   ```bash
   sudo nginx -t
   ```

5. **Restart Nginx**:

   If the test is successful, restart Nginx to apply the changes:

   ```bash
   sudo systemctl restart nginx
   ```

### Accessing the Application

- You can access your application by navigating to `http://your_public_ip` in your web browser.
- The React frontend will be served, and API requests will be proxied to your Spring Boot backend.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.
