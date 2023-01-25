package niffler.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static niffler.config.NifflerConfigImpl.*;

public class NifflerLoginTest {

    //    @Disabled
    @Test
    void mainPageShouldBeDisplayedAfterSuccessLogin() {
        Selenide.open(getAppUrl());
        login();
    }

    protected static void login() {
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(getUsername());
        $("input[name='password']").setValue(getPassword());
        $("button[type='submit']").click();
        $(".header__title").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Niffler. The coin keeper."));
    }
}
