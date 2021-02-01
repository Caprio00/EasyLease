package com.easylease.EasyLease.systemtesting.advisor.ordervalidation;

import com.easylease.EasyLease.model.DBPool.DbConnection;
import com.easylease.EasyLease.model.order.DbOrderDao;
import com.easylease.EasyLease.model.order.Order;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.util.concurrent.TimeUnit;

/**
 * System Test that tests the functionality of validation of an order.
 *
 * @author Caprio Mattia
 * @since 0.1
 * @version 0.2
 */
public class ValidationSuccess {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private static DbConnection dbConnection;
  private static Order order;

  @BeforeAll
  static void init() throws Exception {
    dbConnection = DbConnection.getInstance();
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("root");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);
    dbConnection.setDataSource(mysqlDataSource);
    order = DbOrderDao.getInstance().retrieveById("ORd3Jks");
  }

  @BeforeEach
  public void setUp() throws Exception {
    System.setProperty("webdriver.edge.driver",
        "src/test/java/com/easylease/EasyLease/systemtesting/msedgedriver.exe");
    driver = new EdgeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  @DisplayName("ST_ADVISOR_1_03")
  public void testValidationSuccess() throws Exception {
    driver.get("http://localhost:8080/EasyLease_war_exploded/HomePageServlet");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("marcoGreco@easylease.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pass");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//a[contains(@href, '#')]")).click();
    driver.findElement(By.linkText("Ordini e Preventivi")).click();
    Thread.sleep(500);
    driver.findElement(By.cssSelector("body"))
        .sendKeys(Keys.CONTROL, Keys.HOME);
    driver.findElement(By.id("ORd3Jks")).click();
    driver.findElement(By.id("validation")).click();
    Thread.sleep(500);
    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
    Thread.sleep(500);
    driver.findElement(By.id("date")).click();
    driver.findElement(By.id("date")).clear();
    driver.findElement(By.id("date")).sendKeys("20-05-2021");
    driver.findElement(By.id("validation")).click();
    Thread.sleep(500);
    driver.findElement(By.cssSelector("body"))
        .sendKeys(Keys.CONTROL, Keys.HOME);
    Thread.sleep(500);
    driver.findElement(By.xpath("//a[contains(@href, '#')]")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  @AfterEach
  public void tearDown() throws Exception {
    DbOrderDao.getInstance().update(order);
    driver.quit();
  }
}
