package niffler.api;

import com.github.javafaker.Faker;
import io.restassured.common.mapper.TypeRef;
import niffler.model.Category;
import niffler.model.CategoryJson;
import niffler.model.CurrencyValues;
import niffler.model.SpendJson;

import java.util.Date;
import java.util.List;

import static niffler.api.Spec.request;
import static niffler.config.NifflerConfigImpl.getUsername;
import static niffler.utilities.Utilities.randomDoubleFrom;
import static niffler.utilities.Utilities.randomEnum;

public class ApiRequests {

    public static void getSpends() {

        request()
                .queryParams("username", getUsername())
                .get("/spends")
                .then()
                .log().body()
                .statusCode(200)
        ;
    }

    public static void postSpends() {

        SpendJson spendJson = new SpendJson(
                new Date(),
                randomEnum(Category.class),
                randomEnum(CurrencyValues.class),
                randomDoubleFrom(1.0, 50.0),
                new Faker().beer().name(),
                getUsername());

        request()
                .body(spendJson)
                .post("/addSpend")
                .then()
                .log().body()
                .statusCode(201);
    }

    public static List<CategoryJson> getAllCategories() {
        return request()
                .get("/categories")
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(new TypeRef<List<CategoryJson>>() {
                });
    }

    public static void postCategory(Category description) {
        request()
                .body(new CategoryJson().setDescription(description))
                .post("/category")
                .then()
                .log().body()
                .statusCode(200);
    }

}