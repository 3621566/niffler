package niffler.pages;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import niffler.AppManager;
import niffler.model.CurrencyValues;
import niffler.model.UserJson;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static niffler.api.restassured.UserServiceApi.getCurrentUser;
import static niffler.api.restassured.UserServiceApi.updateUserInfo;
import static niffler.utilities.NamedBy.named;
import static niffler.utilities.Utilities.randomEnum;

public class ProfilePage extends BasePage<ProfilePage> {

    AppManager app;

    public ProfilePage(AppManager app) {
        this.app = app;
    }

    private SelenideElement nameField = $(named(By.name("firstname")).as("'Name' input field")),
            surnameField = $(named(By.name("surname")).as("Surname input field")),
            currencySelectedField = $(named(By.cssSelector("div .select-wrapper")).as("Currency input field")),
            categoryInput = $(named(By.cssSelector("input[name='category']")).as("Category DropDown")),
            submitButton = $(named(By.xpath("//button[contains(text(),'Submit')]")).as("Submit button")),
            naddmeField = $(named(By.xpath("firstname")).as("Name input field"));

    public ProfilePage fillProfile() {
        nameField.sendKeys(new Faker().name().firstName());
        surnameField.sendKeys(new Faker().name().lastName());
        submitButton.click();
        return this;
    }


    @Step("Verify updated user info")
    public ProfilePage verifyUpdatedUserInfo() {
        Faker faker = new Faker();
        UserJson currentUserInfoUi = getCurrentUserInfoUi();
        UserJson currentUserInDb = getCurrentUser();
        currentUserInDb.setFirstname(faker.name().firstName()).setSurname(faker.name().lastName()).setCurrency(randomEnum(CurrencyValues.class));
        updateUserInfo(currentUserInDb);
        refresh();
        UserJson updatedUserInfo = getCurrentUserInfoUi();
        if (currentUserInfoUi.equals(updatedUserInfo)) {
            allureLog("user info  should not matched");
        } else {
            allureLog(currentUserInfoUi.toString());
            allureLog(updatedUserInfo.toString());
            Assertions.fail("user info not matched");
        }
        return this;
    }

    @Step("Get User info from UI")
    private UserJson getCurrentUserInfoUi() {
        return new UserJson()
                .setFirstname(nameField.getText())
                .setSurname(surnameField.getText())
                .setCurrency(CurrencyValues.valueOf(currencySelectedField.getText()))
                ;
    }
}