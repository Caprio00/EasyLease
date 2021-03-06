package com.easylease.easylease.control.client;

import com.easylease.easylease.control.utility.IdGenerator;
import com.easylease.easylease.model.client.Client;
import com.easylease.easylease.model.client.DbClientDao;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SignInServlet", value = "/SignInServlet")
public class SignInServlet extends HttpServlet {
  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    SimpleDateFormat htmlFormat = new SimpleDateFormat("yyyy-MM-dd");
    Client client = new Client();
    client.setIdUser("CL" + IdGenerator.randomIdGenerator());
    client.setFirstName(request.getParameter("name"));
    client.setSurname(request.getParameter("surname"));
    client.setEmail(request.getParameter("email"));
    client.setBirth_place(request.getParameter("birthplace"));
    try {
      client.setBirth_date(htmlFormat.parse(request.getParameter("birthdate")));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    client.setKind(request.getParameter("kind"));
    client.setCity(request.getParameter("city"));
    client.setPc(request.getParameter("pc"));
    client.setStreet(request.getParameter("street"));

    DbClientDao dao = (DbClientDao) DbClientDao.getInstance();

    if (dao.retrieveByEmail(client.getEmail()) != null) {
      request.getSession().setAttribute("exist", "exist");
      request.getRequestDispatcher("/client/signIn.jsp")
          .forward(request, response);
    } else {
      dao.insert(client, request.getParameter("password"));
      request.getSession().setAttribute("ok", "ok");
      request.getRequestDispatcher("/user/login.jsp")
          .forward(request, response);
    }

  }
}
