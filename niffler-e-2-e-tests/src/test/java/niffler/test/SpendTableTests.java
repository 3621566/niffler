package niffler.test;


import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.open;
import static niffler.config.NifflerConfigImpl.getAppUrl;
import static niffler.test.NifflerLoginTest.login;

public class SpendTableTests extends BaseTest {

    @Test()
    void testSpendsTable() {
        open(getAppUrl());
        login();
        $$x("//tbody//tr").shouldHave(sizeGreaterThan(0));
    }
}