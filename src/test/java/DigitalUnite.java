import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v133.css.model.CSSContainerQuery;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DigitalUnite {
    WebDriver driver ;
    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void visitUrl(){
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        String titleActual = driver.getTitle();
        String titleExpected = "Practice webform for learners | Digital Unite";
        assertTrue(titleActual.equals(titleExpected));
    }
    @DisplayName("Fillup the User Form ")
    @Test
    public void fillupUserForm() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        List<WebElement> txtfileds = driver.findElements(By.className("form-control"));
        txtfileds.get(0).sendKeys("Shabit Rony");
        txtfileds.get(1).sendKeys("01704670100");
        txtfileds.get(2).sendKeys("07/17/2002");
        txtfileds.get(3).sendKeys("shabit12@gmail.com");
        txtfileds.get(4).sendKeys("I am a Student. Currently I am doing a training at Road To SDET under Salman Bhai.");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys("C:\\Users\\Sabit\\Documents\\Screenshot 2025-04-08 191052.png");
//        WebElement checkbox = driver.findElement(By.id("edit-age"));
        WebElement checkbox = driver.findElement(By.id("edit-age"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", checkbox);
        List<WebElement> buttons = driver.findElements(By.cssSelector("[type=submit]"));
        js.executeScript("arguments[0].click();", buttons.get(1));
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String successfulMessageActual = driver.findElement(By.cssSelector(".region.region-title")).getText();
        String successfulMessageExpected="Thank you for your submission!";
        System.out.println(successfulMessageActual);
        Assertions.assertTrue(successfulMessageActual.contains(successfulMessageExpected));
    }
    @AfterAll
    public void tearDown() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
    }
}

