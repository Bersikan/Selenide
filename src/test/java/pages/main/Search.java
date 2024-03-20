package pages.main;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Search {

    public static SelenideElement menuButtonProduct = $x(".//button[contains(text(), 'Product')]");
    public static SelenideElement menuEntrieActions = $x(".//div[@class][contains(text(), 'Actions')]");
    public static ElementsCollection productMenuItems = $$x(".//a[contains(@class, 'HeaderMenu-dropdown-link')]");

}
