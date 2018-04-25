package SearchTest;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class WorkingSearch {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String SearchResult;

  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    SearchResult = "S-01";
  }

  @Test
  public void testWorkingSearch() throws Exception {
    driver.get("http://localhost:8080/JEE-ConnectTo/faces/index.xhtml");
    driver.findElement(By.name("j_username")).click();
    driver.findElement(By.name("j_username")).clear();
    driver.findElement(By.name("j_username")).sendKeys("admin");
    driver.findElement(By.name("j_password")).clear();
    driver.findElement(By.name("j_password")).sendKeys("1234");
    driver.findElement(By.name("j_password")).sendKeys(Keys.ENTER);
    driver.findElement(By.linkText("Services")).click();
    driver.findElement(By.linkText("Search a service")).click();
    driver.findElement(By.id("j_idt29:search")).click();
    driver.findElement(By.id("j_idt29:search")).clear();
    driver.findElement(By.id("j_idt29:search")).sendKeys("S-01");
    driver.findElement(By.id("j_idt29")).submit();
    String message = driver.findElement(By.xpath("//table[@id='j_idt29:output']/tbody/child::tr[1]/child::td[1]")).getText();
    Assert.assertTrue("result not found", SearchResult.contains(message));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
