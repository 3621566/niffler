package niffler.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static niffler.config.NifflerConfigImpl.getPassword;
import static niffler.config.NifflerConfigImpl.getUsername;

public class LoginPage extends BasePage<LoginPage> {
    public MainPage login() {
        return login(getUsername(), getPassword());
    }

    @Step("Login with credentials: {username}:{password}")
    public MainPage login(String username, String password) {
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(username);
        $("input[name='password']").setValue(password);
        $("button[type='submit']").click();
        $(".header__title").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Niffler. The coin keeper."));
        return mainPage();
    }
}
