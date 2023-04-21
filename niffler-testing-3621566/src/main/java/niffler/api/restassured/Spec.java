package niffler.api.restassured;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static niffler.api.restassured.ReportTemplate.filters;
import static niffler.config.NifflerConfigImpl.getApiSpendUrl;
import static niffler.config.NifflerConfigImpl.getApiUserDataUrl;

public class Spec {

    public static RequestSpecification spendRequest() {
        return given()
                .header("Content-type", "application/json")
                .baseUri(getApiSpendUrl())
                .filter(filters().customTemplates())
                .log().uri();
    }

    public static RequestSpecification userdataRequest() {
        return given()
                .header("Content-type", "application/json")
                .baseUri(getApiUserDataUrl())
                .filter(filters().customTemplates())
                .log().uri();
    }
}