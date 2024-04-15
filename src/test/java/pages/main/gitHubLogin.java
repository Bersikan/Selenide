package pages.main;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class gitHubLogin {

    public static SelenideElement fieldLogin = $("#login_field");
    public static SelenideElement fieldPwd = $("#password");
    public static SelenideElement buttonSignIn = $(By.name("commit"));
    public static SelenideElement errorFrame = $("[class= 'flash flash-full flash-error  ']");
    public static SelenideElement buttonErrorFrameClose = $("[class='flash flash-full flash-error  ']");

    public static void logIn (String login, String password){
        fieldLogin.val(login);
        fieldPwd.val(password);
        buttonSignIn.click();
    }
}
