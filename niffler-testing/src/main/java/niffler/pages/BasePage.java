package niffler.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static niffler.config.NifflerConfigImpl.getAppUrl;

public class BasePage<T extends BasePage> {

    StartPage startPage;
    LoginPage loginPage;
    MainPage mainPage;
    ProfilePage profilePage;


    public T waitForPageLoaded() {
        //do smth
        return (T) this;
    }

    @Step("Open Niffler")
    public StartPage openApplication() {
        open(getAppUrl());
        return startPage();
    }

    public StartPage startPage() {
        return startPage == null ? new StartPage() : startPage;
    }

    public MainPage mainPage() {
        return mainPage == null ? new MainPage() : mainPage;
    }

    public LoginPage loginPage() {
        return loginPage == null ? new LoginPage() : loginPage;
    }

    public ProfilePage profilePage() {
        return profilePage == null ? new ProfilePage() : profilePage;
    }

    public ProfilePage openProfile() {
        open(getAppUrl() + "/profile");
        return profilePage();
    }

    @Step("{0}")
    public static void allureLog(final String message) {
        //intentionally empty
    }
}
