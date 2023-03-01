package niffler.test;

import io.qameta.allure.junit5.AllureJunit5;
import niffler.AppManager;
import niffler.jupiter.SuiteExtension;
import niffler.model.TestData;
import niffler.pages.StartPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static niffler.utilities.DriverUtils.configureDriver;

@ExtendWith({SuiteExtension.class, AllureJunit5.class})
public abstract class BaseTest {

    AppManager niffler = new AppManager(new TestData());

    @BeforeAll
    static void beforeAll() {
        configureDriver();
    }

    @BeforeEach
    void setUp() {
    }

    protected StartPage niffler() {
        return niffler.openApplication();
    }

    protected StartPage niffler(TestData testData) {
        niffler.setTestData(testData);
        return niffler();
    }
}
