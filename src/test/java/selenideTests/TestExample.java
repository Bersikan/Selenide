package selenideTests;

import selenideConfig.BaseConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static data.ErrorMessages.INCORRECT_LOGIN_MESSAGE;
import static data.RandomDataHelper.randomAlpha;
import static pages.main.gitHubLogin.*;

public class TestExample extends BaseConfig {

    @BeforeClass
    public void gitHubSpecification() {
        open("https://github.com/login");
    }

    @Test(description = "should return a proper error on incorrect login data")
    public void incorrectLogIn() {
        logIn(randomAlpha(10), randomAlpha(10));
        errorFrame.shouldHave(text("asdasd"));
        buttonErrorFrameClose.click();
        errorFrame.shouldNotBe(visible);
    }
}
