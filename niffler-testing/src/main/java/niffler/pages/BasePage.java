package niffler.pages;

import static com.codeborne.selenide.Selenide.open;
import static niffler.config.NifflerConfigImpl.getAppUrl;

public class BasePage<T extends BasePage> {

    LoginPage loginPage;
    MainPage mainPage;
    ProfilePage profilePage;


    public T waitForPageLoaded() {
        //do smth
        return (T) this;
    }

    public LoginPage openApplication() {
        open(getAppUrl());
        return loginPage();
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
}
