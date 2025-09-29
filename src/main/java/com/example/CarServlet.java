/*
 * @Author: sooriyaprakash sooriya.prakash@bluescopetech.com
 * @Date: 2025-09-29 11:58:43
 * @LastEditors: sooriyaprakash sooriya.prakash@bluescopetech.com
 * @LastEditTime: 2025-09-29 14:06:51
 * @FilePath: \servlet-demo-tutorial\servlet-demo\src\main\java\com\example\CarServlet.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/cars")
public class CarServlet extends HttpServlet {

    
    private static final String JDBC_URL = "jdbc:h2:~/testdb"; 
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "";

    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Car List</title></head><body>");
        out.println("<h1>Car Name & Price List</h1>");
        out.println("<table border='1' cellpadding='10'>");
        out.println("<tr><th>ID</th><th>Name</th><th>Price</th></tr>");

        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String sql = "SELECT * FROM CAR"; // CAR table exists in testdb
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                int price = rs.getInt("PRICE");
                out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" + price + "</td></tr>");
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<tr><td colspan='3'>Error: " + e.getMessage() + "</td></tr>");
        }

        out.println("</table>");
        out.println("</body></html>");
    }
}
