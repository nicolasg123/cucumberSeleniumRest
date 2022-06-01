package devsavantautomationtest;


        import io.cucumber.java.After;
        import io.cucumber.java.en.Given;
        import io.cucumber.java.en.Then;
        import io.cucumber.java.en.When;
        import org.apache.commons.io.FileUtils;
        import org.openqa.selenium.*;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.interactions.Actions;
        import java.io.File;
        import java.io.IOException;

public class GoogleTest {

    private WebDriver driver = new ChromeDriver();
    private static final String URL = "https://google.com";
    private static final Integer pauseTime = 3000;

    @Given("google search page")
    public void googleSearchPage() {
        driver.get(URL);
    }

    @When("I search {string} content \"I'm Feeling Lucky\" search button")
    public void iSearchFor(String keyword) {
        final WebElement element = driver.findElement(By.name("q"));
        final Actions action = new Actions(driver);
        action.sendKeys(element, keyword)
                .sendKeys(Keys.ESCAPE)
                .click(driver.findElement(By.xpath("(//input[@name='btnI'])[2]")))
                .pause(pauseTime)
                .perform();
    }

    @Then("I am taken directly to the most relevant result")
    public void iAmTakenScreenShootMostRelevantResults() throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        final File source = screenshot.getScreenshotAs(OutputType.FILE);
        final File destination = new File("./target/SearchResultScreenShoot.png");
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            throw new IOException("Error copying file " + e.getMessage());
        }
    }
}
