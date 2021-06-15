package testing2.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import testing2.config.WebDriverConfig;

public class FacebookTestingApp {
    private static final String XPATH_COOKIES = "//div[contains(@class, \"_9o-r\")]/button[2]";
    private static final String XPATH_CREATEACC = "//div[contains(@class, \"6ltg\")]/a";
    private static final String XPATH_FIRSTNAME = "//div[contains(@class, \"_58mf\")]/div/div[1]/form/div[1]/div/div/div[1]/div/input";
    private static final String XPATH_LASTNAME = "//div[contains(@class, \"_58mf\")]/div/div[1]/form/div[1]/div/div/div[2]/div/div/input";
    private static final String XPATH_DAYSELECT = "//div[contains(@class, \"_58mf\")]/div/div[1]/form/div[1]/div[5]/div[2]/span/span/select[1]";
    private static final String XPATH_MONTHSELECT = "//div[contains(@class, \"_58mf\")]/div/div[1]/form/div[1]/div[5]/div[2]/span/span/select[2]";
    private static final String XPATH_YEARSELECT = "//div[contains(@class, \"_58mf\")]/div/div[1]/form/div[1]/div[5]/div[2]/span/span/select[3]";


    public static void main(String[] args) {
        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.get("https://www.facebook.com");

        WebElement cookiesButton = driver.findElement(By.xpath(XPATH_COOKIES));
        cookiesButton.click();

        WebElement createAccount = driver.findElement(By.xpath(XPATH_CREATEACC));
        createAccount.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement firstName = driver.findElement(By.xpath(XPATH_FIRSTNAME));
        firstName.click();
        firstName.sendKeys("testFirstName");

        WebElement lastName = driver.findElement(By.xpath(XPATH_LASTNAME));
        lastName.click();
        lastName.sendKeys("testLastName");

        WebElement elementDay = driver.findElement(By.xpath(XPATH_DAYSELECT));
        Select selectDay = new Select(elementDay);
        selectDay.selectByIndex(1);

        WebElement elementMonth = driver.findElement(By.xpath(XPATH_MONTHSELECT));
        Select selectMonth = new Select(elementMonth);
        selectMonth.selectByValue("12");

        WebElement elementYear = driver.findElement(By.xpath(XPATH_YEARSELECT));
        Select selectYear = new Select(elementYear);
        selectYear.selectByValue("1905");




    }
}
