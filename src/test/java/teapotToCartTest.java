import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class teapotToCartTest {
    private WebDriver driver;


    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "/Users/marina.rimasha/IdeaProjects/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://www.1a.lv/");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_products_text_field")));

        List<WebElement> banner = driver.findElements(By.cssSelector(".floating-element:nth-child(1)"));
        if (banner.size() > 0); {
            driver.findElement(By.cssSelector(".close-button")).click();
        }

        //mouse over cart
        WebElement element = driver.findElement(By.cssSelector(".items"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();

        List<WebElement> emptyCart = driver.findElements(By.cssSelector(".empty")); //empty cart

       if ( emptyCart.size() == 0); {
      //  click remove item until cart empty
         while  (driver.findElement(By.linkText("×")).isDisplayed());
            {
                driver.findElement(By.linkText("×")).click();
            }
       }
    }

    @After
    public void tearDown()
    {
        driver.quit(); //can comment for some debug actions
    }

    @Test
    public void addTeapotToCart() {
        // Test name: teapotPurchase1a

        // 4 | click | id=search_products_text_field |  | click into the Search field
        driver.findElement(By.id("search_products_text_field")).click();

        // 6 | type | id=search_products_text_field | Tējkanna | type Tējkanna into the search field
        driver.findElement(By.id("search_products_text_field")).sendKeys("Tējkanna");
        // 7 | click | css=.search-inner > input:nth-child(9) |  | Click search (magnifier icon) button
        driver.findElement(By.cssSelector(".search-inner > input:nth-child(9)")).click();
        List<WebElement> banner = driver.findElements(By.cssSelector(".floating-element:nth-child(1)"));

        if (banner.size() > 0); {
            driver.findElement(By.cssSelector(".close-button")).click();
        }

        // 11 | click | css=#search-category-image-grid > .ait-search-result-image-box:nth-child(1) > .ait-search-result-title |  | click "elektriskas tejkannas: category
        driver.findElement(By.cssSelector("#search-category-image-grid > .ait-search-result-image-box:nth-child(1) > .ait-search-result-title")).click();
        // 12 | assertElementPresent | css=.paging-holder:nth-child(20) .showing |  | wait for "1 -  no ... " count displayed
        {
            List<WebElement> elements = driver.findElements(By.cssSelector(".paging-holder:nth-child(20) .showing"));
            assert(elements.size() > 0);
        }
        // 13 | click | css=.product:nth-child(1) .button > span |  | click "ielikt groza"
        driver.findElement(By.cssSelector(".product:nth-child(1) .button > span")).click();
        // 14 | waitForElementVisible | css=h1 | 30000 | wait for "mans pirkumu grozs" to be visible
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        }
        // 15 | mouseOver | css=.items |  | set mouse on to cart
        {
            WebElement element = driver.findElement(By.cssSelector(".items"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        // 16 | assertElementNotPresent | css=.cartdrop li:nth-child(2) |  | assert there is no 2nd item in cart
        {
            List<WebElement> elements = driver.findElements(By.cssSelector(".cartdrop li:nth-child(2)"));
            assert(elements.size() == 0);
        }

    }
}

