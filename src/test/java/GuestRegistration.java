import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GuestRegistration {
    WebDriver driver;

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//    @Test
//    public void visitUrl(){
//        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
//        String titleActual = driver.getTitle();
//        String titleExpected = "Guest Registration Form – User Registration";
//        Assertions.assertTrue(titleActual.equals(titleExpected));
//    }
    @Test
    public void fillupUserForm() throws InterruptedException {
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        List<WebElement> txtFields = driver.findElements(By.tagName("input"));
        txtFields.get(4).sendKeys("Shakib");
        txtFields.get(7).sendKeys(("Khan"));
        txtFields.get(5).sendKeys("shakibkhann1222331223@gmail.com");
        txtFields.get(6).sendKeys(("@shakib1234$$"));
        txtFields.get(8).click();
//        txtFields.get(11).sendKeys("2000-07-17");
        WebElement dobField = txtFields.get(11);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='2000-07-17';", dobField);
        txtFields.get(14).sendKeys("88001714298058");
        txtFields.get(16).sendKeys("Bangladeshi");

        Select option = new Select(driver.findElement(By.id("country_1665629257")));
        option.selectByVisibleText("Bangladesh");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,1500)");

        txtFields.get(33).click();
        List<WebElement> btnSubmit = driver.findElements(By.tagName("button"));
        btnSubmit.get(4).click();
       String successfulMessageActual = driver.findElement(By.id("ur-submit-message-node")).getText();
       String messageExpected = "User successfully registered.";
        System.out.println(successfulMessageActual);
        Assertions.assertTrue(successfulMessageActual.contains(messageExpected));
    }
//    @AfterAll
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
