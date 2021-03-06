package com.easylease.easylease.control.advisor;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.easylease.easylease.model.advisor.Advisor;
import com.easylease.easylease.model.advisor.AdvisorDao;
import com.easylease.easylease.model.advisor.DbAdvisorDao;
import com.easylease.easylease.model.client.Client;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

/**
 * Test of the ClientsServlet class.
 *
 * @author Antonio Sarro
 * @version 0.1
 * @since 0.1
 */
class ClientsServletTest {
  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private ServletContext context;
  @Mock
  private HttpSession session;
  @Mock
  private RequestDispatcher dispatcher;

  private ClientsServlet servlet;
  private AdvisorDao advisorDao;
  private final Map<String, Object> attributes = new HashMap<>();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new ClientsServlet();
    advisorDao = DbAdvisorDao.getInstance();
    when(request.getServletContext()).thenReturn(context);
    when(request.getSession()).thenReturn(session);
    when(context.getContextPath()).thenReturn("");
    when(session.isNew()).thenReturn(true);
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

    Mockito.doAnswer((Answer<Object>) invocation -> {
      String key = (String) invocation.getArguments()[0];
      return attributes.get(key);
    }).when(session).getAttribute(anyString());

    Mockito.doAnswer((Answer<Object>) invocation -> {
      String key = (String) invocation.getArguments()[0];
      Object value = invocation.getArguments()[1];
      attributes.put(key, value);
      return null;
    }).when(session).setAttribute(anyString(), any());
  }

  @AfterEach
  void tearDown() {

  }

  @Test
  void clientsServletTestSuccess() throws ServletException, IOException {
    Advisor advisor = advisorDao.retrieveById("ADJdybc");
    when(request.getSession().getAttribute("user")).thenReturn(advisor);
    servlet.doGet(request, response);
    verify(request).getRequestDispatcher("/advisor/clients.jsp");
  }

  @Test
  void clientsServletTestNullSession() throws ServletException, IOException {
    when(request.getSession()).thenReturn(null);
    servlet.doGet(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void clientsServletTestWrongUser() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(new Client());
    servlet.doGet(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void clientsServletTestNullUser() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(null);
    servlet.doGet(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }
}