
# FoneBook

FoneBook is a Spring Boot-based phone directory application that simplifies your contact management. It provides create, update, and delete capabilities for your phone contacts. Security is enforced by Spring Security, and the user-friendly interface is built with Thymeleaf, making it a convenient tool for getting organized.

## Features

- **Contact Management**: Easily create, update, and delete phone contacts to keep your phonebook organized.

- **Security**: Built-in Spring Security ensures that your data remains private and secure.

- **User-Friendly Interface**: The application boasts a user-friendly interface designed with Thymeleaf for a smooth experience.

## Getting Started

Follow these steps to run the FoneBook application:

### Prerequisites

- Java Development Kit (JDK) 11 or later
- Apache Maven

### Configuration

1. Clone this repository to your local machine.

2. (Optional) Configure the database settings in `src/main/resources/application.properties` if you want to use a specific database:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_database_username
   spring.datasource.password=your_database_password
   ```

3. Build the project using Maven:

   ```
   mvn clean install
   ```

### Running the Application

4. Run the Spring Boot application:

   ```
   mvn spring-boot:run
   ```

The application will start, and you can access it at `http://localhost:8080`.



Feel free to customize this README file further to include any specific instructions or information relevant to your "FoneBook" project.
