📦 Product & Category API Service
🔹 What is this project?
This is a Spring Boot–based REST API service to manage Products and Categories.
Each product belongs to a category, and the API allows creating, updating, retrieving, and deleting products.
The project uses H2 (in-memory database), so it runs instantly without external DB setup.

🛠️ Setup Instructions:
1. Clone the repository
git clone https://github.com/rishiica8/product_category_api.git

2. Move into project folder
cd product_category_api

3. Build the project
mvn clean install

4. Run the application
mvn spring-boot:run

5. Access the application
Runs at: http://localhost:8080


📩 API Endpoints:
1. Create Product
POST /products
Request Body:

{
  "title": "Sample Product",
  "price": 499.99,
  "quantity": 10,
  "category": {
    "title": "Electronics"
  }
}


Response:

{
  "id": 1,
  "title": "Sample Product",
  "price": 499.99,
  "quantity": 10,
  "category": {
    "id": 1,
    "title": "Electronics"
  }
}

2. Get All Products

GET /products

Response:

  {
    "id": 1,
    "title": "Sample Product",
    "price": 499.99,
    "quantity": 10,
    "category": {
      "id": 1,
      "title": "Electronics"
    }
}

3. Get Product by ID

GET /products/{id}

Response (200 OK):

{
  "id": 1,
  "title": "Sample Product",
  "price": 499.99,
  "quantity": 10,
  "category": {
    "id": 1,
    "title": "Electronics"
  }
}


Response (404 Not Found):

{
  "timestamp": "2025-12-11T15:30:45.123",
  "errorMsg": "Product not found",
  "path": "/products/99"
}

4. Update Product

PUT /products/{id}

Request Body:

{
  "title": "Updated Product",
  "price": 549.99,
  "quantity": 15,
  "category": {
    "title": "Gadgets"
  }
}

5. Delete Product

DELETE /products/{id}
Returns 204 No Content

⚠️ Validation Errors (400 Bad Request)

If price or quantity is negative, or title is empty, response looks like:

{
   "timestamp": "2025-12-11T12:56:37.1209651",
    "errorMsg": "Price must be greater than 0",
    "path": "/products"
}

🧰 Technology Stack

-Java 17
-Spring Boot
-Spring Web
-Spring Data JPA
-H2 Database
-Lombok
-Maven

📂 Project Structure
src/
 ├── main/
 │   ├── controller/
 │   ├── service/
 │   ├── repository/
 │   ├── models/
 │   ├── dtos/
 │   ├── exception/
 └── resources/
      └── application.properties
