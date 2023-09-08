# taskManager
Task Manager Spring Boot Application Documentation
Table of Contents
Introduction
Prerequisites
Getting Started
3.1. Cloning the Repository
3.2. Setting up the Database
3.3. Configuring the Application
Running the Application
4.1. Using an IDE (IntelliJ IDEA or STS)
Testing the APIs
5.1. API Documentation
5.2. Testing with Postman
Conclusion
1. Introduction
This documentation provides instructions on how to run the Task Manager Spring Boot Application, set up the necessary environment, and test the APIs. The "TASK MANAGER" is a sample Spring Boot application that demonstrates basic CRUD operations for a hypothetical entity, such as "Task."

Features of the Application:
User Sign-up and Sign-in Feature
CRUD Operation on Tasks
Logout Feature
Securing application with Spring Security
Validations using Spring validations
Global Exception Handling Feature
API Documentation
Dynamic Front-End Pages Using Thymeleaf Template engine
2. Prerequisites
Before you begin, ensure that you have the following prerequisites installed on your system:

Java JDK 17
Apache Maven
MySQL Database
An Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse (optional but recommended)
Postman (for API testing, optional)
3. Getting Started
3.1. Cloning the Repository
Clone the project repository from GitHub:
GitHub Link: https://github.com/IAmTheInfinity24/taskManager

3.2. Setting up the Database
This application uses MySQL Database. Following configuration is required for the database setup:

properties
Copy code
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/taskmanager_db
spring.datasource.username=root
spring.datasource.password=your-password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
3.3. Configuring the Application
You can configure the application properties in the application.properties file located in the src/main/resources directory.

properties
Copy code
# Server Configuration
server.port=8080
server.servlet.context-path=/taskmanager

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
4. Running the Application
4.1 Using an IDE (e.g., IntelliJ IDEA or Eclipse)
Import the project into your IDE. Right-click on the TaskManagerApplication.java class and select "Run" to start the application.

Instructions to Run the Application:

The application will start at the default port 8080.
Access the application by visiting http://localhost:8080/taskmanager/.
The landing page will appear with options for SIGN-IN and SIGN-UP.
After successful sign-up, user details will be stored in the database, and the user can sign in.
Users can create tasks, view all tasks, and log out of the application.
5. Testing the APIs
5.1. API Documentation
The API documentation is generated using Swagger. Once the application is running, you can access the API documentation at: http://localhost:8080/swagger-ui.html

5.2. Testing with Postman
You can use Postman to interact with the APIs. Create appropriate HTTP requests and hit the URLs to access the resources:

GET: /apis/tasks/{taskId} - Retrieve a task by ID
PUT: /apis/tasks/{taskId} - Update a task by ID
DELETE: /apis/tasks/{taskId} - Delete a task by ID
POST: /apis/tasks/new/{userId} - Create a new task for a user
GET: /apis/tasks/user/{userId} - Retrieve all tasks
6. Conclusion
You have successfully set up, run, and tested the "Task Manager" Spring Boot application. Feel free to explore the API documentation and customize the application for your needs. For any issues or questions, please refer to the project's GitHub repository or contact the project creator.

Thank you for using "Task Manager!"
