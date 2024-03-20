package pages.main;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Search {

    public static SelenideElement fieldLogin = $x(".//input[@name='login']");
    public static SelenideElement fieldPwd = $x(".//input[@name='password']");
    public static SelenideElement buttonSignIn = $x(".//input[@name='commit']");
    public static SelenideElement errorFrame = $x(".//div[@class='flash flash-full flash-error  ']");
    public static SelenideElement buttonErrorFrameClose = $x(".//div[@class='flash flash-full flash-error  ']//button");

}
