package niffler.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import niffler.model.SpendJson;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$;
import static niffler.api.restassured.SpendServiceApi.getSpends;
import static niffler.condition.SpendCondition.spends;
import static niffler.utilities.NamedBy.*;

public class MainPage extends BasePage<MainPage> {


    private final ElementsCollection tableSpend = $$(named(xpath("//tbody//tr")).as("table spend")),
            spendingButtons = $$(named(cssSelector(".spendings__buttons button")).as("Spending Buttons")),
            spendsTableBody = $$(named(cssSelector("tbody tr")).as("Spends Body"));

    public MainPage fillNewSpending() {
        // fill
        return this;
    }

    @Step("Verify Spend Table")
    public MainPage verifySpendTable() {
        List<SpendJson> spendsInDb = getSpends();
        int quantityOfSpends = spendsInDb.size();
        allureLog(String.format("%s spends exists in database", quantityOfSpends));
        tableSpend.shouldHave(sizeGreaterThanOrEqual(quantityOfSpends));
        spendsTableBody.shouldHave(spends(spendsInDb));
        return this;
    }
}