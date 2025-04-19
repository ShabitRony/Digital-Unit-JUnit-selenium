import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScrapData {
    WebDriver driver;

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void visitUrl(){
        driver.get("https://dsebd.org/latest_share_price_scroll_by_value.php");
        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> allRows = table.findElements(By.tagName("tr"));

        int i = 1;
        for (WebElement tr : allRows) {
            List<WebElement> cells = tr.findElements(By.tagName("td"));
            for (WebElement td : cells) {
                System.out.println("num[" + i + "] " + td.getText());
                i++;
            }
        }
    }
}
