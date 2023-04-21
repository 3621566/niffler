package niffler.test;

import io.qameta.allure.AllureId;
import niffler.jupiter.annotation.User;
import niffler.model.UserModel;
import niffler.model.UserType;
import org.junit.jupiter.api.Test;

public class UserQueueTests {

    @Test
    @AllureId("1")
    void queueTest1(@User(UserType.ADMIN) UserModel user) {
        System.out.println(user.getUsername() + ":" + user.getPassword());
    }

    @Test
    @AllureId("2")
    void queueTest2(@User(UserType.ADMIN) UserModel user1, @User(UserType.ADMIN) UserModel user2) {
        System.out.println("User 1: " + user1.getUsername() + ":" + user1.getPassword());
        System.out.println(user2.getUsername() + ":" + user2.getPassword());
    }
}
