package projectpackages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.List;
import java.util.Random;

public class MainSeleniumWebDriver {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-bundled-ppapi-flash");
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.get("https://vk.com/");
        deleteRandomFriend(webDriver, getListFriends(webDriver));
    }

    public static List<WebElement> getListFriends(WebDriver driver) {
        driver.findElement(By.xpath("//input[@id='index_email']")).sendKeys("89206265210");
        driver.findElement(By.xpath("//input[@id='index_pass']")).sendKeys("hokage123");
        driver.findElement(By.xpath("//button[@id='index_login_button']")).click();
        WebElement elementFriends = (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='l_fr']/a")));
        elementFriends.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id, 'friends_user_row')]")));
        List<WebElement> listFriends = driver.findElements(By.xpath("//div[contains(@id, 'friends_user_row')]"));
        return listFriends;
    }

    public static void deleteRandomFriend(WebDriver driver, List<WebElement> friends) {
        Random random = new Random();
        String id = friends.get(random.nextInt(friends.size())).getAttribute("id");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.
                findElement(By.xpath("//div[@id='" + id + "']//div[3]//div[1]"))).build().perform();
        driver.findElement(By.xpath("//div[@id='" + id + "']//div[3]//div[2]//a[3]")).click();
        driver.findElement(By.xpath("//li[@id='l_pr']/a/span/span[3]")).click();
    }
}

