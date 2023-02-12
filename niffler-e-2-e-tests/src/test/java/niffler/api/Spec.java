package niffler.api;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static niffler.api.ReportTemplate.filters;
import static niffler.config.NifflerConfigImpl.getApiUrl;

public class Spec {

    public static RequestSpecification request() {
        return given()
                .header("x-api-key", "getApiKey()")
                .header("Content-type", "application/json")
                .baseUri(getApiUrl())
                .filter(filters().customTemplates())
                .log().uri();
    }
}