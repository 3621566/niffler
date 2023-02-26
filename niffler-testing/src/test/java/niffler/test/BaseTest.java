package niffler.test;

import niffler.jupiter.BeforeSuiteExtension;
import niffler.pages.BasePage;
import niffler.pages.LoginPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BeforeSuiteExtension.class)
public abstract class BaseTest {

    BasePage niffler;

    @BeforeAll
    static void beforeAll() {
    }

    @BeforeEach
    void setUp() {
        niffler = new BasePage();
    }

    protected LoginPage niffler() {
        BasePage basePage = niffler == null ? new BasePage() : niffler;
        return basePage.openApplication();
    }
}
