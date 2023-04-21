package niffler.test;


import io.qameta.allure.AllureId;
import niffler.jupiter.extension.SpendExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName("SPEND TESTS")
@ExtendWith({SpendExtension.class})
public class SpendTableTests extends BaseTest {

    @AllureId("2")
    @Test()
    @Tag("spend")
    @DisplayName("Table spend verification")
    void testSpendsTable() {
        niffler()
                .openLoginPage()
                .login()
                .verifySpendTable();
    }
}