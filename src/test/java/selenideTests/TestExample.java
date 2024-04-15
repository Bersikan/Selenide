package selenideTests;

import general_helpers.element.Element;
import general_helpers.element.ElementBuilder;
import org.openqa.selenium.By;
import org.testng.Assert;
import testNG.group_annotations.IntegrationTest;
import testNG.group_annotations.SmokeTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenideConfig.BaseConfig;

import java.util.Objects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static git_hub_selenide.data.ErrorMessages.INCORRECT_LOGIN_MESSAGE;
import static general_helpers.StringHelper.randomAlpha;
import static pages.main.gitHubLogin.*;

public class TestExample extends BaseConfig {

    @BeforeMethod(alwaysRun = true)
    public void gitHubSpecification() {
        open("https://github.com/login");
    }

    @Test()
    @SmokeTest
    public void incorrectLogIn() {
        String browser = System.getenv("browser");
        if(!Objects.equals(browser, "chrome"))
        {
            Assert.fail("asdasdasd");
        }
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
