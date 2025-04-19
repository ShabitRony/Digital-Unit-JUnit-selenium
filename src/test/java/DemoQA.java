import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DemoQA {
    WebDriver driver;
    @BeforeAll
    public  void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @DisplayName("Fillup the User form")
    @Test
     public void visitUrl(){
        driver.get("https://demoqa.com/");
        String titleActual= driver.getTitle();
        String titleExpected = "DEMOQA";

        Assertions.assertTrue((titleActual.equals(titleExpected)));
    }
    @Test
    public void fillupUserForm(){
        driver.get("https://demoqa.com/text-box");
//        driver.findElement(By.id("userName")).sendKeys("Test User");
//    driver.findElement(By.cssSelector("[type=text]")).sendKeys("Test User");
           List<WebElement> txtfields= driver.findElements(By.className("form-control"));
           txtfields.get(0).sendKeys("Test User");
           txtfields.get(1).sendKeys("Test@gmail.com");
           txtfields.get(2).sendKeys("Gulshan");
           txtfields.get(3).sendKeys("Dhaka");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");

          List<WebElement> btnSubmit =driver.findElements(By.tagName("button"));
                btnSubmit.get(1).click();
          List<WebElement> txtResult = driver.findElements(By.tagName("p"));
          String nameActual = txtResult.get(0).getText();
          String emailActual = txtResult.get(1).getText();
          String cAddressActual = txtResult.get(2).getText();
          String pAddressActual = txtResult.get(3).getText();

          String nameExpected = "Test User";
          String emailExpected = "Test@gmail.com";
          String cAddressExpected = "Gulshan";
          String pAddressExpected = "Dhaka";

          Assertions.assertTrue(nameActual.contains(nameExpected));
          Assertions.assertTrue(emailActual.contains(emailExpected));
          Assertions.assertTrue(cAddressActual.contains(cAddressExpected));
          Assertions.assertTrue(pAddressActual.contains(pAddressExpected));
    }


//    @AfterAll
    public void tearDown(){
        driver.quit();
    }
}
