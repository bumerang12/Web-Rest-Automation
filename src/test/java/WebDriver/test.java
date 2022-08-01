package WebDriver;

import org.testng.annotations.Test;

import static WebDriver.BasePage.driver;


public class test {

    CaseMethod caseMethod = new CaseMethod(driver);

    @Test(priority = 0)
    public void controlSearchingArea(){
        caseMethod = new CaseMethod(driver);
        caseMethod.SearchArea();
    }

    @Test (priority = 1)
    public void checkHappyText(){
        caseMethod = new CaseMethod(driver);
        caseMethod.CheckHappy();
    }

    @Test (priority = 2)
    public void verifyDocumentB(){
        caseMethod = new CaseMethod(driver);
        caseMethod.verifyDocument();
    }
}
