<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
</head>

<body>
    <header>
        <h1>E-Commerce Web Application</h1>
    </header>
    <section>
        <h2>Features</h2>
        <ul>
            <li><strong>User Authentication:</strong> Users can create accounts, log in, and log out.</li>
            <li><strong>Product Catalog:</strong> Browse a catalog of products with details such as name, category, price, and discounts.</li>
            <li><strong>Shopping Cart:</strong> Add products to the shopping cart for future purchase.</li>
            <li><strong>Quantity Management:</strong> Easily increase or decrease the quantity of items in the cart.</li>
            <li><strong>Checkout:</strong> Place orders for the items in the cart.</li>
            <li><strong>Order History:</strong> View a history of past orders.</li>
        </ul>
    </section>
    <section>
        <h2>Technologies Used</h2>
        <p>
            <strong>Java:</strong> Backend development using Java programming language.<br>
            <strong>Servlets and JSP:</strong> Server-side processing and dynamic web page generation.<br>
            <strong>Hibernate:</strong> Object-relational mapping for database interactions.<br>
            <strong>MySQL:</strong> Database for storing user, product, cart, and order information.
        </p>
    </section>
    <section>
        <h2>Project Structure</h2>
        <ul>
            <li><code>src:</code> Contains Java source code.</li>
            <li><code>WebContent:</code> Contains web resources such as JSP files, CSS, and JS.</li>
            <li><code>hibernate.cfg.xml:</code> Hibernate configuration file.</li>
            <li><code>pom.xml:</code> Maven project configuration file.</li>
        </ul>
    </section>

 <section>
        <h2>Setup</h2>
        <ol>
            <li><strong>Database Configuration:</strong>
                <ul>
                    <li>Set up a MySQL database and update the database configuration in <code>hibernate.cfg.xml</code>.</li>
                    <li>Execute the SQL scripts in the <code>database-scripts</code> folder to create the necessary tables.</li>
                </ul>
            </li>
            <li><strong>Maven Dependencies:</strong> This project uses Maven for dependency management. Ensure Maven is installed, and run <code>mvn clean install</code> to download the required dependencies.</li>
            <li><strong>Run the Application:</strong>
                <ul>
                    <li>Deploy the application on a servlet container (e.g., Apache Tomcat).</li>
                    <li>Access the application at <code>http://localhost:8080/ecommerce_website</code>.</li>
                </ul>
            </li>
        </ol>
    </section>
    <section>
        <h2>How to Contribute</h2>
        <p>
            Contributions to enhance and improve the E-Commerce web application are welcome. Follow these steps:
        </p>
        <ol>
            <li>Fork the repository.</li>
            <li>Create a new branch (<code>git checkout -b feature/improvement</code>).</li>
            <li>Make changes and commit (<code>git commit -m 'Add new feature'</code>).</li>
            <li>Push to the branch (<code>git push origin feature/improvement</code>).</li>
           
