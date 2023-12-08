E-Commerce Web Application
It is a simple web application for online shopping that allows users to browse products, add items to their cart, and place orders.

Features
User Authentication: Users can create accounts, log in, and log out.
Product Catalog: Browse a catalog of products with details such as name, category, price, and discounts.
Shopping Cart: Add products to the shopping cart for future purchase.
Quantity Management: Easily increase or decrease the quantity of items in the cart.
Checkout: Place orders for the items in the cart.
Order History: View a history of past orders.
Technologies Used
Java: Backend development using Java programming language.
Servlets and JSP: Server-side processing and dynamic web page generation.
Hibernate: Object-relational mapping for database interactions.
MySQL: Database for storing user, product, cart, and order information.
Project Structure
src: Contains Java source code.
com.mycart.dao: Data Access Objects for database operations.
com.mycart.entities: Entity classes representing database tables.
com.mycart.helper: Helper classes for setting up Hibernate.
com.mycart.servlets: Servlets for handling HTTP requests.
WebContent: Contains web resources such as JSP files, CSS, and JS.
hibernate.cfg.xml: Hibernate configuration file.
pom.xml: Maven project configuration file.
Setup
Database Configuration:

Set up a MySQL database and update the database configuration in hibernate.cfg.xml.
Execute the SQL scripts in the database-scripts folder to create the necessary tables.
Maven Dependencies:

This project uses Maven for dependency management. Ensure Maven is installed, and run mvn clean install to download the required dependencies.
Run the Application:

Deploy the application on a servlet container (e.g., Apache Tomcat).
Access the application at http://localhost:8080/ecommerce_website.
How to Contribute
Contributions to enhance and improve the E-Commerce web application are welcome.
Follow these steps:
Fork the repository.
Create a new branch (git checkout -b feature/improvement).
Make changes and commit (git commit -m 'Add new feature').
Push to the branch (git push origin feature/improvement).
Create a pull request.
