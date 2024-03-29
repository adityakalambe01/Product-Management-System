
---

# Product Management System

## Tech Stack Used
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white) &nbsp;
![Spring](https://img.shields.io/badge/spring%20-%6DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white) &nbsp;
![SpringBoot](https://img.shields.io/badge/spring_boot%20-%6DB33F.svg?&style=for-the-badge&logo=springboot&logoColor=white) &nbsp;
![Hibernate](https://img.shields.io/badge/hibernate_&_JPA_Repository%20-gray.svg?&style=for-the-badge&logo=hibernate&logoColor=white) &nbsp;

## Tools Used
![Intellij Idea Ultimate](https://img.shields.io/badge/intellij_idea_Ultimate-000000.svg?style=for-the-badge&logo=intellijidea&logoColor=white) &nbsp;
![Postman](https://img.shields.io/badge/postman-FF6C37.svg?style=for-the-badge&logo=postman&logoColor=white) &nbsp;

---

## Running the Application

To run the application, locate the file `ProductsApplication.java` in the directory `src/main/java/com/products` and execute it.

1. If you are using Eclipse IDE / Intellij Idea with Postman then follow <a href="https://www.postman.com/adityakalambe20/workspace/product-management-system">this document</a>.<br> 


[//]: # (Alternatively, you can run the `Products-0.0.1-SNAPSHOT.jar` file using the command `java -jar Products-0.0.1-SNAPSHOT.jar`.)

---
2. If you are using Intellij Idea + Restful Tool(Intellij Idea Plugin) then follow this readme.

## CRUD Operations on Categories

1. **Add Category (POST)** <br>
   URL: `http://localhost:8080/api/categories` <br>
   Body:
    ```json
    {
        "categoryId": 0,
        "categoryName": "String",
        "categoryDescription": "String",
        "products": []
    }
    ```

2. **Update Category (PUT)** <br>
   URL: `http://localhost:8080/api/categories/{id}` <br>
   Body:
    ```json
    {
        "id": 0,
        "categoryId": 0,
        "categoryName": "String",
        "categoryDescription": "String",
        "products": []
    }
    ```

3. **Get All Categories (GET)** <br>
   a. 
   URL: `http://localhost:8080/api/categories` <br>
   Description: Using this you will get first 5 categories
   <br><br>
   b. URL: `http://localhost:8080/api/categories?page=pageNumber` <br>
   Description: Using this you will get 5 categories of page 0 and you can change page like `http://localhost:8080/api/categories?page=1`
<br><br>
   c. URL: `http://localhost:8080/api/categories?page=pageNumber&size=pageSize` <br>
   Description: Using this you will get 5 categories of page 0 with default page size 5 and you can change page and size like `http://localhost:8080/api/categories?page=1&size=10` and you will get page 1 and 10 categories will be displayed
   <br><br>
4. **Get Category by Id (GET)** <br>
   URL: `http://localhost:8080/api/categories/{id}` <br>
   Body:
    ```json
   { 
       "id": 0 
   }
   ```
5. **Delete Category by Id (DELETE)** <br>
   a. URL: `http://localhost:8080/api/categories/{id}` <br>
   Body: 
   ```json
   { 
       "id": 0 
   }
   ```
   Description: Deletes the category with the specified ID if the product table does not contain entries associated with this category. If products exist, the category will not be deleted. <br>
   <br>
   b. URL: `http://localhost:8080/api/categories/{id}?force=true` <br>
   Body: 
   ```json
   { 
       "id": 0 
   }
   ```
   Description: Deletes the category with the specified ID along with associated products, regardless of whether products exist or not.
---

## CRUD Operations on Products

1. **Add Default Products (GET)** <br>
   URL: `http://localhost:8080/api/products/addDefault`

2. **Add New Product (POST)** <br>
   URL: `http://localhost:8080/api/products` <br>
   Body:
    ```json
    {
        "id": 0,
        "name": "Product Name",
        "cost": 0.0,
        "category": {
            "categoryId": 0,
            "categoryName": "Category Name",
            "categoryDescription": "Category Description",
            "products": []
        }
    }
    ```
   or
   ```json
   {
        "id": 0,
        "name": "Product Name",
        "cost": 1542.0,
        "category": {
             "categoryId": 1
        }
   }
   ```
   Note: category id is mandatory
   <br><br>
3. **Get Product by Id (GET)** <br>
   URL: `http://localhost:8080/api/products/{id}` <br>
   Body: 
   ```json
   { 
      "id": 0 
   }
   ```
4. **Get All Products (GET)** <br>
   a.
   URL: `http://localhost:8080/api/products` <br>
   Description: Using this you will get first 5 products
   <br><br>
   b. URL: `http://localhost:8080/api/products?page=pageNumber` <br>
   Description: Using this you will get 5 products of page 0 and you can change page like `http://localhost:8080/api/products?page=1`
   <br><br>
   c. URL: `http://localhost:8080/api/categories?page=pageNumber&size=pageSize` <br>
   Description: Using this you will get 5 products of page 0 with default page size 5 and you can change page and size like `http://localhost:8080/api/products?page=1&size=10` and you will get page 1 and 10 products will be displayed
   <br><br>

5. **Delete Product by Id (DELETE)** <br>
   URL: `http://localhost:8080/api/products/{id}` <br>
   Body: 
   ```json
   { 
      "id": 0 
   }
   ```

6. **Update Product by Id (PUT)** <br>
   URL: `http://localhost:8080/api/products/{id}` <br>
   Body:
    ```json
    {
        "id": 0,
        "name": "String",
        "cost": 0.0,
        "category": {
            "categoryId": 0,
            "categoryName": "String",
            "categoryDescription": "String",
            "products": []
        }
    }
    ```

---

This documentation provides a comprehensive overview of the endpoints and operations available in the Product Management System.

___

### Below Links shows results of each request's send from postman/restfulTool
   1. <a href="https://documenter.getpostman.com/view/30255039/2sA2xnwUcQ">Category Documentation</a>
   2. <a href="https://documenter.getpostman.com/view/30255039/2sA2xnwUXy">Product Documentation</a>

---