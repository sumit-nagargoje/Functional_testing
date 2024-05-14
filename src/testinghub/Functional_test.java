package testinghub;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class Functional_test {

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\nagar\\Desktop\\software\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.dealsdray.com/");

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("prexo.mis@dealsdray.com");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("prexo.mis@dealsdray.com");
        driver.findElement(By.xpath("//button[@class='MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButtonBase-root  css-1usxxvf']")).click();

        // Go on Order Button
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            WebElement firstButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root']/div/div/div/div[2]/div/div[2]/button")));
            firstButton.click();

            WebElement secondButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root']/div/div/div/div[2]/div/div[2]/div/div/a/button")));
            secondButton.click();

            WebElement thirdButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root']/div/div[2]/div[2]/div/div/div[2]/div[2]/button")));
            thirdButton.click();

            WebElement fileInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mui-7")));
            fileInput.sendKeys("C:\\Users\\nagar\\Downloads\\demo-data.xlsx");

            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButtonBase-root  css-6aomwy']")));
            submitButton.click();
            submitButton.click();  // Click twice as in original code

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Handle potential alert
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert text: " + alert.getText());
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No alert present, continue with further actions
        }

        // Capture screenshot
        screenshot(driver);
        
       
    }

    public static void screenshot(WebDriver driver) throws IOException {
    	  String screenshotDir = "C:\\Users\\nagar\\Downloads\\sample\\Desktop Windos\\";
          String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
          File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
          Files.copy(screenshotFile, new File(screenshotDir + "screenshot_" + timestamp + ".png"));
          
          // Close the browser
          driver.quit();
    }
    
    
}
