# H2 Table Data API with Java Servlet

## Description
This project demonstrates how to fetch data from an H2 database and expose it via a Java Servlet API running on Apache Tomcat. The API returns data from a database table in HTML format (or JSON if modified).

**Repository Purpose:**  
Provide a simple example of connecting a Java Servlet to an H2 database and serving table data through a web endpoint.

---

## Features
- Connects to an **H2 database** using JDBC.
- Fetches all rows from a table (e.g., `car`).
- Exposes a **GET API endpoint** via Servlet.
- Deployable on **Apache Tomcat**.

---

## Table Example

```sql
CREATE TABLE car (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    color VARCHAR(20)
);

INSERT INTO car(name, color) VALUES
('Honda Civic', 'Red'),
('Toyota Corolla', 'Blue'),
('Ford Mustang', 'Black');

Technologies Used

Java 17+

Jakarta Servlet API

H2 Database

Apache Tomcat 10+

Maven (for dependency management and building WAR)


Project Setup

Clone the repository:

git clone <repository-url>
cd <project-folder>


Build the WAR file:

mvn clean package


Deploy WAR to Tomcat:

Copy the generated *.war file from target/ to TOMCAT_HOME/webapps/.

Start Tomcat:

TOMCAT_HOME/bin/startup.bat  # Windows
# or
TOMCAT_HOME/bin/startup.sh   # Linux/Mac


Access the API:

http://localhost:8080/<your-war-name>/cars


You will see the table data displayed in HTML format.

Servlet Code Overview
@WebServlet("/cars")
public class CarServlet extends HttpServlet {
    private Connection conn;

    @Override
    public void init() throws ServletException {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM car");
        // output table in HTML
    }

    @Override
    public void destroy() {
        conn.close();
    }
}

Notes

This project uses embedded H2 database (~/test), so data persists in your home directory.

For modern JDBC, Class.forName("org.h2.Driver") is optional, but included for compatibility.

You can modify the Servlet to return JSON instead of HTML for API use in frontend applications.
