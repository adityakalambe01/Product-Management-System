# ProductCategoryRelationshipOneTOMany

### how to save data into categories

  1. PostMapping <br>
  `
  http://localhost:8080/api/categories/
  `
  <br>
     {<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "categoryId": 0,<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "categoryName": "String",<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "categoryDescription": "String",<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "products": []<br>
     }<br><br>
  2. PutMapping<br>
  `
     http://localhost:8080/api/categories/{id} 
  `
  <br>
     {<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "id": 0,<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "categoryId": 0,<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "categoryName": "String",<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "categoryDescription": "String",<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "products": []<br>
     }<br><br>
  3. GetMapping<br>
  `
     http://localhost:8080/api/categories/ 
  `<br><br>
  4. GetMapping<br>
  `
  http://localhost:8080/api/categories/{id}
  `
  <br>
     {<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "id": 0<br>
     }<br><br>
  5. DeleteMapping<br>
  `http://localhost:8080/api/categories/{id}`
  <br>
     {<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "id": 0<br>
     }<br>
  &nbsp;

### how to save data into products
  1. PostMapping<br>
    `http://localhost:8080/api/products/`<br>
     {<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "id": 0,<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "name": "String",<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "cost": 0.0,<br>
     &nbsp;&nbsp;&nbsp;&nbsp; "category": {<br>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "categoryId": 0,<br>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "categoryName": "String",<br>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "categoryDescription": "String",<br>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "products": []<br>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
     }<br>