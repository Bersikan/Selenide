package tests;

import configurations.BaseConfig;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static data.ErrorMessages.INCORRECT_LOGIN_MESSAGE;
import static data.RandomDataHelper.randomAlpha;
import static pages.main.Search.*;

public class TestExample extends BaseConfig {

    @Test(description = "should return a proper error on incorrect login data")
    public void incorrectLogIn() {
        fieldLogin.val(randomAlpha(10));
        fieldPwd.val(randomAlpha(10));
        buttonSignIn.click();
        errorFrame.shouldHave(text(INCORRECT_LOGIN_MESSAGE));
        buttonErrorFrameClose.click();
        errorFrame.shouldNotBe(visible);
    }
}
