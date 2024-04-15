package selenideTests;

import com.codeborne.selenide.Configuration;
import general_helpers.element.Element;
import general_helpers.element.ElementBuilder;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenideConfig.BaseConfig;
import testNG.group_annotations.IntegrationTest;
import testNG.group_annotations.SmokeTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static general_helpers.StringHelper.randomAlpha;
import static git_hub_selenide.data.ErrorMessages.INCORRECT_LOGIN_MESSAGE;
import static pages.main.gitHubLogin.*;

public class TestExample extends BaseConfig {

    @BeforeMethod(alwaysRun = true)
    public void gitHubSpecification() {
        open("https://github.com/login");
    }

    @Test()
    @SmokeTest
    public void incorrectLogIn() {
        logIn(randomAlpha(10), randomAlpha(10));
        errorFrame.shouldHave(text(INCORRECT_LOGIN_MESSAGE));
        buttonErrorFrameClose.click();
        errorFrame.shouldNotBe(visible);
    }

    @Test
    @IntegrationTest
    public void incorrectLogIn2() {
        logIn(randomAlpha(10), randomAlpha(10));
        errorFrame.shouldHave(text(INCORRECT_LOGIN_MESSAGE));
        buttonErrorFrameClose.click();
        errorFrame.shouldNotBe(visible);
    }

    @Test
    @IntegrationTest
    public void builderPattern() {
        By section = By.cssSelector("main");
        By sebSection = By.cssSelector(".auth-form");
        By element = By.cssSelector("#login_field");

        ElementBuilder elb = new ElementBuilder();
        Element sect = elb.setSection(section)
                .setSubSection(sebSection)
                .setElement(element).build();
        sect.init().val("Some login");


    }
}
