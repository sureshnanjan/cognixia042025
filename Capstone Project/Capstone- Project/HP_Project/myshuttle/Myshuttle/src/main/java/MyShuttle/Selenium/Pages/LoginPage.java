package MyShuttle.Selenium.Pages;

import MyShuttle.Operations.LoginPageOperations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WebElementInteractions;

public class LoginPage implements LoginPageOperations {

    private WebDriver driver;

    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//input[@value='Log in']");
    private By pageTitle = By.xpath("/html/head/title");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://34.93.0.171:8080/myshuttledev");
        this.driver.manage().window().maximize();
//          WebElementInteractions.navigatetoUrlUtil("http://34.93.0.171:8080/myshuttledev",driver);
    }


    @Override
    public String getLoginPageTitle() {
        WebElement titleElem = WebElementInteractions.findElementUtil(pageTitle, driver);
        return WebElementInteractions.getTextUtil(titleElem);
    }


    @Override
    public void enterEmail(String email) {
        WebElement emailElem = WebElementInteractions.findElementUtil(emailField, driver);
        if(emailElem != null){
            emailElem.clear();
            emailElem.sendKeys(email);
        }
    }

    @Override
    public void enterPassword(String password) {
        WebElement passwordElem = WebElementInteractions.findElementUtil(passwordField, driver);
        if(passwordElem != null){
            passwordElem.clear();
            passwordElem.sendKeys(password);
        }
    }

    @Override
    public void clickLogin() {
        WebElement loginBtn = WebElementInteractions.findElementUtil(loginButton, driver);
        WebElementInteractions.clickElementUtil(loginBtn);
    }


}
