package niffler.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$x;

public class MainPage extends BasePage<MainPage> {


    private final ElementsCollection tableSpend = $$x("//tbody//tr");

    public MainPage fillNewSpending() {
        // fill
        return this;
    }

    @Step("Verify Spend Table")
    public MainPage verifySpendTable() {
        tableSpend.shouldHave(sizeGreaterThan(0));
        return this;
    }
}
