package pages.main;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class gitHubLogin {

    public static SelenideElement fieldLogin = $("#login_field");
    public static SelenideElement fieldPwd = $("#password");
    public static SelenideElement buttonSignIn = $("div.position-relative input[name='commit']");
    public static SelenideElement errorFrame = $(".flash.flash-full.flash-error");
    public static SelenideElement buttonErrorFrameClose = errorFrame.$(".flash-close");

    public static void logIn(String login, String password) {
        fieldLogin.val(login);
        fieldPwd.val(password);
        buttonSignIn.click();
    }
}
