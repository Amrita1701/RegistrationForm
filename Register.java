package com.example.regf;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Register extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet servlet1</title>");
        out.println("</head>");
        out.println("<body>");
        //Getting all the incoming details from the request.....

        String name = req.getParameter("user_name");
        String pass = req.getParameter("user_password");
        String email = req.getParameter("user_email");

        try {
            addUserToDB(name, pass, email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        out.println(name);
        out.println(pass);
        out.println(email);

        out.println("</body>");
        out.println("</html>");
    }



    public void addUserToDB(String name, String pass, String email)throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/amrita/Documents/javaProjects/RegF/reg.db");
        PreparedStatement pstmt = conn.prepareStatement("insert into users(name, password, email) values(?, ?, ?)");
        pstmt.setString(1, name);
        pstmt.setString(2, pass);
        pstmt.setString(3, email);
        pstmt.executeUpdate();
    }
}
