package web;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    static final String urlLoginPage = "https://test-stand.gb.ru/login";
    static String username = "silinaleksei";
    static String password = "8ff3ea1e1e";

    @FindBy(how = How.XPATH, using = "//*[@id='login']/div[1]//input")
    private SelenideElement inputUsername;

    @FindBy(how = How.XPATH, using = "//*[@id='login']/div[2]//input")
    private SelenideElement inputPassword;

    @FindBy(how = How.CLASS_NAME, using = "mdc-button__label")
    private SelenideElement btnLogin;

    @FindBy(how = How.XPATH, using = "//*[@id='app']//nav//li[3]/a")
    static SelenideElement linkUser;

    @FindBy(how = How.XPATH, using = "//*[@class='error-block svelte-uwkxn9']/p[1]")
    static SelenideElement message;


    public LoginPage enterUsername(String username) {
        inputUsername.setValue(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        inputPassword.setValue(password);
        return this;
    }

    public MainPage clickBtnLogin() {
        btnLogin.click();
        return page(MainPage.class);
    }

    public static void login() {
        open(urlLoginPage, LoginPage.class)
                .enterUsername(username)
                .enterPassword(password)
                .clickBtnLogin();
    }

    public static void login(String username, String password) {
        open(urlLoginPage, LoginPage.class)
                .enterUsername(username)
                .enterPassword(password)
                .clickBtnLogin();
    }

    public static String getTextLinkUser() {
        return linkUser.getText();
    }

    public static String getTextMessage() {
        return message.getText();
    }
}
