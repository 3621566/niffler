package niffler.api.restassured;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static niffler.api.restassured.ReportTemplate.filters;
import static niffler.config.NifflerConfigImpl.getApiUrl;

public class Spec {

    public static RequestSpecification request() {
        return given()
                .header("Content-type", "application/json")
                .baseUri(getApiUrl())
                .filter(filters().customTemplates())
                .log().uri();
    }
}