import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataScrap {

    WebDriver driver;

    @BeforeAll
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://dsebd.org/latest_share_price_scroll_by_value.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void WriteFile() throws IOException {
        String filePath = "F:\\JUnit\\JUnit\\src\\test\\java\\dse_share_data.txt";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        List<WebElement> tableContainer = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.className("table-responsive"))));
        WebElement table = tableContainer.get(0);

        FileWriter writer = new FileWriter(filePath);

        scrapData(writer, table);

    }

    public void scrapData(FileWriter writer, WebElement table) throws IOException {
        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

        int rowNum = 1;

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int cellNum = 1;
            for (WebElement cell : cells) {
                String cellText = cell.getText().trim();
                writer.write(cellText + "\t");
                System.out.print(cellText + "\t");
                cellNum++;
            }
            writer.write("\n");
            System.out.println();
            rowNum++;
        }

    }

    @AfterAll
    void tearDown() {
        driver.quit();
    }
}