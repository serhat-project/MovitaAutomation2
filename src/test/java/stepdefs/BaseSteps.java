package stepdefs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Utilities.Driver.getDriver;
import static Utilities.Driver.getWait;

public class BaseSteps {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BaseSteps(){
        driver = getDriver();
        wait = getWait();
    }

    public void click(By locator){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        click(element);
    }


    public void click(WebElement element){
        wait.until(driver1 -> {
           try {
               element.click();
               return true;
           }catch (Exception e1){
               try {
                   new Actions(driver1).moveToElement(element).click().perform();
                   return true;
               }catch (Exception e2){
                   try {
                       ((JavascriptExecutor) driver1).executeScript("arguments[0].click()", element);
                       return true;
                   }catch (Exception e3){
                       return false;
                   }
               }
           }
        });

    }



    public void sendkeys(By locator, String text){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        sendkeys(element, text);
    }

    public void sendkeys(WebElement element, String text){
        wait.until(driver1 -> {
            try {
                element.clear();
                element.sendKeys(text);
                return true;
            }catch (Exception e1){
                try {
                    element.clear();
                    new Actions(driver1).moveToElement(element).sendKeys(text).perform();
                    return true;
                }catch (Exception e2){
                    try {
                        element.clear();
                        ((JavascriptExecutor) driver1).executeScript("arguments[0].value='" + text + "'", element);
                        return true;
                    }catch (Exception e3){
                        return false;
                    }
                }
            }
        });

    }

    public void waitForVisibility(By locator){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    public void waitForVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }


}
