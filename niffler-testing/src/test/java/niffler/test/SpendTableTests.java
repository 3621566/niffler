package niffler.test;


import io.qameta.allure.AllureId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SPEND TESTS")
public class SpendTableTests extends BaseTest {

    @AllureId("2")
    @Test()
    void testSpendsTable() {
        niffler()
                .login()
                .verifySpendTable()
        ;
    }
}