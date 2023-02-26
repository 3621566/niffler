package niffler.test;

import io.qameta.allure.junit5.AllureJunit5;
import niffler.jupiter.SuiteExtension;
import niffler.pages.BasePage;
import niffler.pages.StartPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static niffler.utilities.DriverUtils.configureDriver;

@ExtendWith({SuiteExtension.class, AllureJunit5.class})
public abstract class BaseTest {

    BasePage niffler;

    @BeforeAll
    static void beforeAll() {
        configureDriver();
    }

    @BeforeEach
    void setUp() {
        niffler = new BasePage();
    }

    protected StartPage niffler() {
        BasePage basePage = niffler == null ? new BasePage() : niffler;
        return basePage.openApplication();
    }
}
