package WebDriver;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;


public class CaseMethod extends BasePage {

    public CaseMethod(WebDriver driver) {super(driver);}


    public void SearchArea() {
        CaseMethod.driverSetUp("https://www.lely.com/en");
        CaseMethod.tryCatch(2000);
        CaseMethod.closeCookiesFrame();
        clickElementByXpath("//div[@class = 'header-navigation-button']");
        CaseMethod.tryCatch(2000);

        /*******     2 Way to check Search area appeared   *********/
        /***   FIRST   ***/
        WebElement isDisplayedSearchButton = driver.findElement(new By.ByCssSelector("form[data-do='global-search-dropdown']"));
        String isDisplay = isDisplayedSearchButton.getAttribute("class");
        System.out.println("dropdown Active control = " + isDisplay);

        /********   SECOND   ******/
        WebElement isDisplayedSearchButton1 = driver.findElement(By.xpath("//p[@class = 'form-field']"));
        Boolean isDisplayedVerify = isDisplayedSearchButton1.isDisplayed();
        System.out.println("Search box display = " + isDisplayedVerify);

    }



    public void CheckHappy() {
        CaseMethod.driverSetUp("https://www.lely.com/en");
        CaseMethod.tryCatch(2000);
        CaseMethod.closeCookiesFrame();
        clickElementByXpath("//div[@class = 'header-navigation-button']");
        CaseMethod.tryCatch(2000);
        WebElement sendTextBox = driver.findElement(By.id("global-search"));
        sendTextBox.sendKeys("happy");
        clickElementByCss("button[class='button button-tertiary']");
        CaseMethod.tryCatch(2000);
        happyControlAtPage(Page.PAGE1);
        happyControlAtPage(Page.PAGE2);
        happyControlAtPage(Page.PAGE3);
        happyControlAtPage(Page.PAGE4);
        /* S SSS*/

    }





    public void verifyDocument() {
        CaseMethod.driverSetUp("https://www.lely.com/techdocs/");
        CaseMethod.tryCatch(2000);
        CaseMethod.closeCookiesFrame();
        clickElementByID("select2-id_q-container");
        WebElement searchDropDown = driver.findElement(new By.ByCssSelector("input[class='select2-search__field']"));
        searchDropDown.sendKeys("Luna EUR");
        CaseMethod.tryCatch(2000);
        driver.findElement(new By.ByCssSelector("input[class='select2-search__field']")).sendKeys(Keys.ENTER);

        boolean isDisplayed = driver.findElement(new By.ByCssSelector("section[class='tab-content is-active']")).isDisplayed();

        if (isDisplayed == true) {
            System.out.println("content of the catalogs are visible");
        } else {
            System.out.println("No content here!");
        }

        CaseMethod.tryCatch(2000);

        List<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
        System.out.println(" Tab Count Before click View Document = " + tabs1.size());

        String locator = "a[target='_blank']";
        By locatorView = new By.ByCssSelector(locator);

        WebElement viewDocument = driver.findElement(new By.ByCssSelector(locator));
        CaseMethod.scrollToElement(locatorView);
        CaseMethod.tryCatch(3000);
        viewDocument.click();
        CaseMethod.tryCatch(4000);

        List<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        System.out.println(" Tab Count After click View Document  = " + tabs2.size());
        if (tabs2.size() > tabs1.size()) {
            System.out.println("Opened New Tab");
        } else {
            System.out.println("Not create new tab!");
        }
        driver.switchTo().window(tabs2.get(0));
        clickElementByCss("a[class='button button-secondary icon-pdf']");
        System.out.println(CaseMethod.getDownloadsPath());
        boolean B = CaseMethod.isFileDownloaded(CaseMethod.getDownloadsPath(), "D-S006VT_-.pdf");
        System.out.println("Verify document downloaded = " + B);
    }





}
