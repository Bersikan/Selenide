package tests;

import configurations.BaseConfig;
import org.testng.annotations.Test;

import static pages.main.Search.*;

public class TestExample extends BaseConfig {

    @Test
    public void asd() {
        menuButtonProduct.click();
        assert menuEntrieActions.isDisplayed();
        assert productMenuItems.size() == 28;
    }
}
