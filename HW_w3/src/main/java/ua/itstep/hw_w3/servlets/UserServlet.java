/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ua.itstep.hw_w3.servlets;

/**
 *
 * @author pronc
 */
import com.google.inject.Singleton;
import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// @WebServlet("/user")
@Singleton
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UserServlet::doGet");

        String name = req.getParameter("name");
        if (name == null || name.isBlank()) {
            name = "User";
        }

        req.setAttribute("userName", name);
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }
}
