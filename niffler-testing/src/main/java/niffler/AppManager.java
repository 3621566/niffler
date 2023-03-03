package niffler;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import niffler.model.TestData;
import niffler.pages.LoginPage;
import niffler.pages.MainPage;
import niffler.pages.ProfilePage;
import niffler.pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static niffler.config.NifflerConfigImpl.getAppUrl;

public class AppManager {
    private @Getter
    @Setter TestData testData;

    public AppManager(TestData testData) {
        this.testData = testData;
    }

    LoginPage loginPage;
    MainPage mainPage;
    ProfilePage profilePage;
    StartPage startPage;

    public LoginPage loginPage() {
        return loginPage == null ? new LoginPage(this) : loginPage;
    }

    public MainPage mainPage() {
        return mainPage == null ? new MainPage(this) : mainPage;
    }

    public ProfilePage profilePage() {
        return profilePage == null ? new ProfilePage(this) : profilePage;
    }

    public StartPage startPage() {
        return startPage == null ? new StartPage(this) : startPage;
    }

    @Step("Open Niffler")
    public StartPage openApplication() {
        open(getAppUrl());
        return startPage();
    }

    public ProfilePage openProfile() {
        open(getAppUrl() + "/profile");
        return profilePage();
    }
}
