package niffler.api.restassured;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import niffler.model.CategoryJson;
import niffler.model.CategoryValues;
import niffler.model.CurrencyValues;
import niffler.model.SpendJson;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static niffler.api.restassured.Spec.request;
import static niffler.config.NifflerConfigImpl.getUsername;
import static niffler.utilities.Utilities.*;

public class SpendServiceApi {

    @Step("GET: Spend List")
    public static List<SpendJson> getSpends() {

        return request()
                .param("username", getUsername())
                .get("/spends")
                .then()
//                .log().body()
                .statusCode(200)
                .extract().as(new TypeRef<List<SpendJson>>() {
                });
    }

    public static SpendJson postRandomSpend() {
        SpendJson spendJson = new SpendJson(
                getFormattedDate(),
                randomEnum(CategoryValues.class),
                randomEnum(CurrencyValues.class),
                randomDoubleFrom(1.0, 50.0),
                new Faker().beer().name(),
                getUsername());
        return postSpends(spendJson);
    }

    public static List<SpendJson> postRandomSpends(int quantityOfSpends) {
        return IntStream.range(0, quantityOfSpends).mapToObj(i -> postRandomSpend()).toList();
    }

//    public static

    @Step("POST: Spend")
    public static SpendJson postSpends(SpendJson spendJson) {
        return request()
                .body(spendJson)
                .log().body()
                .post("/addSpend")
                .then()
                .log().body()
                .statusCode(201)
                .extract().as(SpendJson.class);
    }

    @Step("GET: All Categories")
    public static List<CategoryJson> getAllCategories() {
        return request()
                .param("username", getUsername())
                .get("/categories")
                .then()
//                .log().body()
                .statusCode(200)
                .extract().as(new TypeRef<List<CategoryJson>>() {
                });
    }

    @Step("POST: Category: {name} for User: {username}")
    public static CategoryJson postCategory(CategoryValues name, String username) {
        return request()
                .body(new CategoryJson().setCategory(name).setUsername(username))
//                .log().body()
                .post("/category")
                .then()
//                .log().body()
                .statusCode(200)
                .extract().as(CategoryJson.class);
    }

    public static CategoryJson postCategory(CategoryValues categoryName) {
        return postCategory(categoryName, getUsername());
    }

    public static void createCategoriesApi() {
        List<CategoryValues> existingCategoriesInDbByUser = getAllCategories().stream().map(CategoryJson::getCategory).toList();
        List<CategoryValues> requiredCategories = Arrays.asList(CategoryValues.values());
        List<CategoryValues> categoriesShouldBeCreated = existingCategoriesInDbByUser.stream().filter(category -> !requiredCategories.contains(category))
                .toList();
        categoriesShouldBeCreated.forEach(SpendServiceApi::postCategory);
    }
}