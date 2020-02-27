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

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ctcoVacancySkillsTest {

    private WebDriver driver;

    static final By careersTabXPATH= By.xpath("//a[contains(@href, 'https://ctco.lv/careers/')]");
    static final By vacanciesXPATH = By.xpath("//a[@href='https://ctco.lv/careers/vacancies/']");
    static final By testAutomationEngineerXPATH = By.xpath("//a[text()='Test Automation Engineer']");
    static final By skillsText = By.xpath("//h1[text()='Test Automation Engineer']/following-sibling::div/p[3]");

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
    public void testEngineerSkillsWithLocators() {

        Actions builder = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("https://ctco.lv/");
        driver.manage().window().setSize(new Dimension(1200, 920));

        WebElement careersButton = driver.findElement(careersTabXPATH);
        careersButton.isDisplayed(); //wait for Carreers tab top be displayed
        builder.moveToElement(careersButton).perform(); //move to it


        WebElement vacanciesButton = driver.findElement(vacanciesXPATH);
        vacanciesButton.isDisplayed();
        builder.moveToElement(vacanciesButton).perform();
        vacanciesButton.click();

        WebElement testEngineerButton = driver.findElement(testAutomationEngineerXPATH);
        wait.until(ExpectedConditions.visibilityOf(testEngineerButton));
        testEngineerButton.click();

        WebElement requiredSkills = driver.findElement(skillsText);
        wait.until(ExpectedConditions.visibilityOfElementLocated(skillsText));

        assertThat(requiredSkills.getText(), containsString("Good level in English reading /speaking /writing"));
        assertThat(requiredSkills.getText(), containsString("Team player with good analytical and communication skills"));
        assertThat(requiredSkills.getText(), containsString("Experience with automated testing tools and frameworks"));

    }

/*   -- TEST DESIGN --

 - Go to https://ctco.lv in browser
 - Wait for "All vacancies" button to appear
 - Click on "All vacancies" button
 - Wait for "Test Automation Engineer" link to appear
 - Click on "Test Automation Engineer" link

 - Assert there is text present
 	- "Good level in English reading /speaking /writing"
	- "Experience with automated testing tools and frameworks"
	- "communication skills;"
 */

    @Test
    public void testEngineerSkills() {
        // Test name: testEngineerSkills
        // Step # | name | target | value | comment
        // 1 | open | / |  |
        driver.get("https://ctco.lv/");
        // 2 | setWindowSize | 1108x864 |  |
        driver.manage().window().setSize(new Dimension(1108, 864));
        // 3 | waitForElementPresent | css=.col-sm-9 > .text-block .button-bg | 30000 | Wait for "All vacancies" button to appear
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".col-sm-9 > .text-block .button-bg")));
        }
        // 4 | click | css=.col-sm-9 > .text-block .button-bg |  | Click on "All vacancies"
        driver.findElement(By.cssSelector(".col-sm-9 > .text-block .button-bg")).click();
        // 5 | waitForElementPresent | linkText=Test Automation Engineer | 30000 | Wait for "Test Automation Engineer" vacancy to be in list
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Test Automation Engineer")));
        }
        // 6 | click | linkText=Test Automation Engineer |  | Click the Automation Engineer vacancy
        driver.findElement(By.linkText("Test Automation Engineer")).click();
        // 7 | waitForElementVisible | css=.animated > .text-block p:nth-child(3) | 30000 | wait for vacancy skills to be visible
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".animated > .text-block p:nth-child(3)")));
        }
        // 8 | assertText | css=.animated > .text-block p:nth-child(3) | Assert skills required includes English, frameworks and communication skills |
        assertThat(driver.findElement(By.cssSelector(".animated > .text-block p:nth-child(3)")).getText(), containsString("Good level in English reading /speaking /writing"));
        assertThat(driver.findElement(By.cssSelector(".animated > .text-block p:nth-child(3)")).getText(), containsString("Experience with automated testing tools and frameworks"));
        assertThat(driver.findElement(By.cssSelector(".animated > .text-block p:nth-child(3)")).getText(), containsString("communication skills"));
    }

}

