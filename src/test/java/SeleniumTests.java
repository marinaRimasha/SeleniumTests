import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class SeleniumTests {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "/Users/marina.rimasha/IdeaProjects/geckodriver");
        driver = new FirefoxDriver();

    }

    @After
    public void tearDown()
    {
        driver.quit(); //can comment for some debug actions
    }

    @Test
    public void addTeapotToCart() {

    }
}

