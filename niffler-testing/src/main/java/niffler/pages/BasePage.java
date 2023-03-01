package niffler.pages;

import io.qameta.allure.Step;

public class BasePage<T extends BasePage> {



    public T waitForPageLoaded() {
        //do smth
        return (T) this;
    }



    @Step("{0}")
    public static void allureLog(final String message) {
        //intentionally empty
    }
}
