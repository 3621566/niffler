package niffler.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static niffler.utilities.NamedBy.named;

public class StartPage extends BasePage<StartPage> {

    private SelenideElement loginButtonOnWelcomePage = $(named(By.cssSelector("a[href*='redirect']")).as("Login Button"));

    @Step("Open Login Page")
    public LoginPage openLoginPage() {
        loginButtonOnWelcomePage.click();
        return loginPage();
    }
}
