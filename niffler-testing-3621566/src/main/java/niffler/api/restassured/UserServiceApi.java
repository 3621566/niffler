package niffler.api.restassured;


import io.qameta.allure.Step;
import niffler.model.UserJson;

import static niffler.api.restassured.Spec.userdataRequest;
import static niffler.config.NifflerConfigImpl.getUsername;

public class UserServiceApi {

    @Step("POST: Update Current User Information")
    public static void updateUserInfo(UserJson user) {
        userdataRequest()
                .body(user)
                .post("/updateUserInfo")
                .then()
                .statusCode(200);
    }

    @Step("GET: Current User Information")
    public static UserJson getCurrentUser() {
        return userdataRequest()
                .param("username", getUsername())
                .get("/currentUser")
                .then()
                .statusCode(200)
//                .log().body()
                .extract().as(UserJson.class);
    }
}
