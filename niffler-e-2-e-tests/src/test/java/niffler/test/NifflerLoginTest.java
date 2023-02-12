package niffler.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import niffler.jupiter.ScreenshotExtension;
import niffler.jupiter.UsersExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.$;
import static niffler.config.NifflerConfigImpl.*;

@ExtendWith({ScreenshotExtension.class, UsersExtension.class})
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

