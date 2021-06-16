package com.kodilla.testing2.crudapp;

import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import testing2.config.WebDriverConfig;

import java.util.Random;

public class CrudAppTestSuite {
    private static final String BASE_URL = "https://dalmonion.github.io/";
    private WebDriver driver;
    private Random generator;

    @BeforeEach
    public void initTests() {
        driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.get(BASE_URL);
        generator = new Random();
    }

    @AfterEach
    public void cleanUpAfterTest() {
        driver.close();
    }

    private String createCrudAppTestTask() throws InterruptedException {
        final String XPATH_TASK_NAME = "//form[contains(@action, \"createTask\")]/fieldset[1]/input";
        final String XPATH_TASK_CONTENT = "//form[contains(@action, \"createTask\")]/fieldset[2]/textarea";
        final String XPATH_ADD_BUTTON = "//form[contains(@action, \"createTask\")]/fieldset[3]/button";
        String taskName = "Task number " + generator.nextInt(100000);
        String taskContent = taskName + " content";

        WebElement name = driver.findElement(By.xpath(XPATH_TASK_NAME));
        name.sendKeys(taskName);

        WebElement content = driver.findElement(By.xpath(XPATH_TASK_CONTENT));
        content.sendKeys(taskContent);

        WebElement addButton = driver.findElement(By.xpath(XPATH_ADD_BUTTON));
        addButton.click();
        Thread.sleep(2000);

        return taskName;
    }

    private void sendTestTaskToTrello(String taskName) throws InterruptedException {
        driver.navigate().refresh();

        while (!driver.findElement(By.xpath("//select[1]")).isDisplayed()) ;

        driver.findElements(
                By.xpath("//form[@class=\"datatable__row\"]")).stream()
                .filter(anyForm ->
                        anyForm.findElement(By.xpath(".//p[@class=\"datatable__field-value\"]"))
                                .getText().equals(taskName))
                .forEach(theForm -> {
                    WebElement selectElement = theForm.findElement(By.xpath(".//select[1]"));
                    Select select = new Select(selectElement);
                    select.selectByIndex(1);

                    WebElement buttonCreateCard =
                            theForm.findElement(By.xpath(".//button[contains(@class, \"card-creation\")]"));
                    buttonCreateCard.click();
                });
        Thread.sleep(5000);
    }

    private boolean checkTaskInTrello(String taskName) throws InterruptedException {
        final String TRELLO_URL = "https://trello.com/login";
        boolean result = false;
        WebDriver driverTrello = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driverTrello.get(TRELLO_URL);

        driverTrello.findElement(By.id("user")).sendKeys("ddgrudzinski@gmail.com");
        driverTrello.findElement(By.id("password")).sendKeys("601934256");
        WebElement el = driverTrello.findElement(By.id("login"));
        el.submit();

        Thread.sleep(4000);

        driverTrello.findElement(By.id("password")).sendKeys("601934256");
        driverTrello.findElement(By.id("login-submit")).submit();

        Thread.sleep(4000);

        driverTrello.findElements(By.xpath("//a[@class=\"board-title\"]")).stream()
                .filter(aHref -> aHref.findElements(By.xpath(".//div[@title=\"Kodilla Application\"]")).size() > 0)
                .forEach(WebElement::click);

        Thread.sleep(4000);

        result = driverTrello.findElements(By.xpath("//span")).stream()
                .anyMatch(theSpan -> theSpan.getText().equals(taskName));

        driverTrello.close();

        return result;
    }

    private void deleteTaskInTrello(String taskName) throws InterruptedException{
        final String TRELLO_URL = "https://trello.com/login";

        WebDriver driverTrello = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driverTrello.get(TRELLO_URL);

        driverTrello.findElement(By.id("user")).sendKeys("ddgrudzinski@gmail.com");
        driverTrello.findElement(By.id("password")).sendKeys("601934256");
        WebElement el = driverTrello.findElement(By.id("login"));
        el.submit();

        Thread.sleep(4000);

        driverTrello.findElement(By.id("password")).sendKeys("601934256");
        driverTrello.findElement(By.id("login-submit")).submit();

        Thread.sleep(4000);


        WebElement boards = driverTrello.findElement(By.xpath("//a[@class=\"boards-page-board-section-header-options-item\"]"));
        boards.click();

        Thread.sleep(1000);

        WebElement specificBoard = driverTrello.findElement(By.xpath("//div[@title=\"Kodilla Application\"]"));
        specificBoard.click();

        Thread.sleep(2000);

        driverTrello.findElements(By.xpath("//a[@class=\"list-card js-member-droppable ui-droppable\"]")).stream()
                .filter(a -> a.findElement(By.xpath(".//span[@class=\"list-card-title js-card-name\"]")).getText().equals(taskName))
                .forEach(WebElement::click);

        while(!driverTrello.findElement(By.xpath(".//div[@class=\"window-overlay\"]")).isDisplayed());

        WebElement archiveButton = driverTrello.findElement(By.xpath(".//div[@class=\"window-overlay\"]/div[1]/div[1]/div[1]/div[5]/div[5]/div[1]/a[5]"));
        archiveButton.click();
        WebElement deleteButton = driverTrello.findElement(By.xpath(".//div[@class=\"window-overlay\"]/div[1]/div[1]/div[1]/div[5]/div[5]/div[1]/a[7]"));
        deleteButton.click();

        while(!driverTrello.findElement(By.xpath(".//div[@class=\"pop-over is-shown\"]")).isDisplayed());

        WebElement confirmDeleteButton = driverTrello.findElement(By.xpath(".//div[@class=\"pop-over is-shown\"]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]"));
        confirmDeleteButton.click();

        driverTrello.close();
    }

    @Test
    void shouldCreateTrelloCard() throws InterruptedException {
        String taskName = createCrudAppTestTask();
        sendTestTaskToTrello(taskName);
        assertTrue(checkTaskInTrello(taskName));
        deleteTaskInTrello(taskName);
    }

}
