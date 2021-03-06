package com.easylease.easylease.control.user;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Caprio Mattia
 * @version 0.1
 * @since 0.1
 */
@WebServlet(name = "ContattiServlet", urlPatterns = "/ContattiServlet")
public class ContattiServlet extends HttpServlet {
  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = request.getRequestDispatcher(
        "/user/contatti.jsp");
    requestDispatcher.forward(request, response);
  }
}
