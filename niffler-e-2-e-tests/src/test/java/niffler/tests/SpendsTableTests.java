package niffler.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.open;
import static niffler.config.NifflerConfigImpl.getAppUrl;
import static niffler.tests.NifflerLoginTest.login;

public class SpendsTableTests extends BaseTest {

    @Test()
    void testSpendsTable() {
        open(getAppUrl());
        login();
        $$x("//tbody//tr").shouldHave(sizeGreaterThan(0));
    }
}