package com.easylease.easylease.control.advisor;

import com.easylease.easylease.model.advisor.Advisor;
import com.easylease.easylease.model.order.DbOrderDao;
import com.easylease.easylease.model.order.Order;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This Servlet has the task to showing an order at the Advisor.
 *
 * @author Caprio Mattia
 * @version 0.6
 * @since 0.1
 */
@WebServlet(name = "OrderManagementAdvisorServlet", urlPatterns = "/OrderManagementAdvisorServlet")

public class OrderManagementAdvisorServlet extends HttpServlet {
  private final Logger logger = Logger.getLogger(
      OrderManagementAdvisorServlet.class.getName());

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (session != null) {
      try {
        if (!(session.getAttribute("user") instanceof Advisor)
            || session.getAttribute("user") == null) {
          throw new ServletException("Section dedicated to a registered user"
              + " on the platform correctly as an Advisor");
        }
        String id = request.getParameter("id_order");
        if (id == null || id.length() != 7 || !id.startsWith("OR")) {
          throw new ServletException("The id sent is incorrect");
        }
        DbOrderDao dbOrderDao = (DbOrderDao) DbOrderDao.getInstance();
        Order order = dbOrderDao.retrieveById(id);
        if (order == null) {
          throw new ServletException("The order doesn't exist");
        }
        request.setAttribute("order", order);
        request.getRequestDispatcher("/advisor/orderManagementAdvisor.jsp")
            .forward(request, response);
      } catch (ServletException e) {
        logger.log(Level.SEVERE, e.getMessage());
        request.getRequestDispatcher("/user/homePage.jsp")
            .forward(request, response);
      }
    } else {
      request.getRequestDispatcher("/user/homePage.jsp")
          .forward(request, response);
    }
  }
}
