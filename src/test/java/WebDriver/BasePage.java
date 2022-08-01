package WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;
import java.util.ArrayList;
import java.util.Locale;


public class BasePage {

    public static WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    static String cookiesElement = "div[class='cookienotice cookienotice-accept cookienotice--active']";
    static String cookiesAcceptButton = "cookienotice-button-accept";

    public static void driverSetUp(String url){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }


    public static void closeCookiesFrame(){
        WebElement controlCookiesFrame = driver.findElement(new By.ByCssSelector(cookiesElement));
        Boolean cookiesFrame = controlCookiesFrame.isDisplayed();

        if (cookiesFrame) {
            WebElement acceptCookies = driver.findElement(By.id(cookiesAcceptButton));
            acceptCookies.click();
            tryCatch(2000);
        }
    }


    public static String getDownloadsPath() {

        String downloadPath = System.getProperty("user.home");
        File file = new File(downloadPath + "/Downloads/");
        return file.getPath();
    }

    public static boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();

        if (dir_contents != null) {
            for (File dir_content : dir_contents) {
                if (dir_content.getName().equals(fileName))
                    return true;
            }
        }
        return false;
    }

    public WebElement findElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }


    public void clickElementByXpath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clickElementByCss(String xpath) {
        driver.findElement(new By.ByCssSelector(xpath)).click();
    }

    public void clickElementByID(String xpath) {
        driver.findElement(By.id(xpath)).click();
    }



    public static void scrollToElement(By locator){
        WebElement elementLocation = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", elementLocation);
    }

    static int counter = 1;

    /** ********  ALTERNATIVE HAPPY CONTROL**************/

//    public static void happyControlAtPage(int n) {
//        if (n == 1) {
//            System.out.println("Page 1");
//        }
//        else if (n == 2) {
//            System.out.println("Page 2");
//            driver.findElement(By.xpath("//a[@href='?q=happy&page=2']")).click();
//        }
//        else if (n == 3) {
//            System.out.println("Page 3");
//            driver.findElement(By.xpath("//a[@href='?q=happy&page=3']")).click();
//        }
//        else {
//            System.out.println("no more page!");
//        }
//        ArrayList<WebElement> pageHappy = (ArrayList<WebElement>) driver.findElements(By.cssSelector("p[class='item-description']"));
//        for (int i = 0; i < pageHappy.size(); i++) {
//            String containsHappy = pageHappy.get(i).getText().toLowerCase(Locale.ROOT);
//            System.out.println((i + counter) + ". index =" + containsHappy);
//            Boolean happy = containsHappy.contains("happy");
//            if (happy) {
//                System.out.println(happy + ". " + (i + counter) + ". index contains searched word 'happy' in description");
//            } else {
//                System.out.println(happy + ". " + (i + counter) + ". index DOES NOT contain searched word 'happy' in description");
//            }
//        }
//        counter += pageHappy.size();
//    }

    /****************/

    enum Page{
        PAGE1, PAGE2, PAGE3, PAGE4;
    }

    public static void happyControlAtPage(Page p) {
        switch (p) {
            case PAGE1:
                ArrayList<WebElement> page1Happy = (ArrayList<WebElement>) driver.findElements(By.cssSelector("p[class='item-description']"));
                for (int i = 0; i < page1Happy.size(); i++)
                {
                    String containsHappy = page1Happy.get(i).getText().toLowerCase(Locale.ROOT);
                    System.out.println((i+counter) +". index =" +containsHappy);
                    Boolean happy = containsHappy.contains("happy");
                    if(happy){
                        System.out.println(happy + ". " + (i+counter) + ". index contains searched word 'happy' in description");
                    }
                    else{
                        System.out.println(happy + ". "+ (i+counter)+". index DOES NOT contain searched word 'happy' in description");
                    }
                }
                counter += page1Happy.size();
                break;
            case PAGE2:
                driver.findElement(By.xpath("//a[@href='?q=happy&page=2']")).click();
                ArrayList<WebElement> page2Happy = (ArrayList<WebElement>) driver.findElements(By.cssSelector("p[class='item-description']"));
                for (int i = 0; i < page2Happy.size(); i++) {
                    String containsHappy = page2Happy.get(i).getText().toLowerCase(Locale.ROOT);
                    System.out.println((i + counter) + ". index =" + containsHappy);
                    Boolean happy = containsHappy.contains("happy");
                    if (happy) {
                        System.out.println(happy + ". " + (i + counter) + ". index contains searched word 'happy' in description");
                    } else {
                        System.out.println(happy + ". " + (i + counter) + ". index DOES NOT contain searched word 'happy' in description");
                    }
                }
                counter += page2Happy.size();
                break;
            case PAGE3:
                driver.findElement(By.xpath("//a[@href='?q=happy&page=3']")).click();
                ArrayList<WebElement> page3Happy = (ArrayList<WebElement>) driver.findElements(By.cssSelector("p[class='item-description']"));
                for (int i = 0; i < page3Happy.size(); i++)
                {
                    String containsHappy = page3Happy.get(i).getText().toLowerCase(Locale.ROOT);
                    System.out.println((i+counter) +". index =" +containsHappy);
                    Boolean happy = containsHappy.contains("happy");
                    if(happy){
                        System.out.println(happy + ". " + (i+counter) + ". index contains searched word 'happy' in description");
                    }
                    else{
                        System.out.println(happy + ". "+ (i+counter)+". index DOES NOT contain searched word 'happy' in description");
                    }
                }
                counter += page3Happy.size();
                break;
            default:
                System.out.println("There is no more page");
        }
    }



    public static void tryCatch(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
