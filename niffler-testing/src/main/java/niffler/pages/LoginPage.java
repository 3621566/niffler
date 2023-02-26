package niffler.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static niffler.config.NifflerConfigImpl.getPassword;
import static niffler.config.NifflerConfigImpl.getUsername;
import static niffler.utilities.NamedBy.named;

public class LoginPage extends BasePage<LoginPage> {

    private SelenideElement loginButtonOnWelcomePage = $(named(By.cssSelector("a[href*='redirect']")).as("Login Button")),
            usernameField = $(named(By.cssSelector("input[name='username']")).as("Username Field")),
            passwordField = $(named(By.cssSelector("input[name='password']")).as("Password Field")),
            submitButton = $(named(By.cssSelector("button[type='submit']")).as("Submit Button")),
            headerTitle = $(named(By.cssSelector(".header__title")).as("Header Title"));

    @Step("Login to Niffler")
    public MainPage login() {
        return login(getUsername(), getPassword());
    }

    @Step("Login with credentials: {username}:{password}")
    public MainPage login(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
        submitButton.click();
        headerTitle
                .shouldBe(visible)
                .shouldHave(text("Niffler. The coin keeper."));
        return mainPage();
    }
}