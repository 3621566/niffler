package niffler.test;

import io.qameta.allure.AllureId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("USER TESTS")
public class UpdateUserInfoTests extends BaseTest {

    @AllureId("3")
    @Test()
    @Tag("user")
    @DisplayName("User Info Verification")
    void testSpendsTable() {
        niffler()
                .openLoginPage()
                .login()
                .openProfile()
                .verifyUpdatedUserInfo();
    }
}