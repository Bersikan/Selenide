package selenideTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import general_helpers.element.Element;
import general_helpers.element.ElementBuilder;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenideConfig.BaseConfig;
import testNG.group_annotations.IntegrationTest;
import testNG.group_annotations.SmokeTest;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static general_helpers.StringHelper.randomAlpha;
import static pages.main.gitHubLogin.*;

public class TestExample extends BaseConfig {

    @BeforeClass(alwaysRun = true)
    public void gitHubSpecification() {
        open("https://www.github.com/login");
    }

    @Test(dataProvider = "emptyFields")
    @SmokeTest
    public void loginWithEmptyFields(String login, String pwd) {
        logIn(login, pwd);
        buttonSignIn.shouldBe(visible);
        buttonSignIn.shouldBe(enabled);
    }

    @DataProvider(name = "emptyFields")
    public Object[][] emptyFields() {
        return new Object[][]{
                {"", randomAlpha(10)},
                {randomAlpha(10), ""},
                {"", ""}
        };
    }

    @Test(dataProvider = "incorrectFields")
    @SmokeTest
    public void loginWithIncorrectData(String login, String pwd) {
        logIn(login, pwd);
        buttonSignIn.shouldBe(visible);
        buttonSignIn.shouldBe(enabled);
        errorFrame.shouldBe(visible);
        buttonErrorFrameClose.click();
    }

    @DataProvider(name = "incorrectFields")
    public Object[][] incorrectFields() {
        return new Object[][]{
                {"CorrectLogin", randomAlpha(10)},
                {randomAlpha(10), randomAlpha(10)},
                {randomAlpha(10), "CorrectPwd"}
        };
    }
}
