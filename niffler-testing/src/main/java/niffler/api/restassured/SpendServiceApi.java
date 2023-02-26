package niffler.api.restassured;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import niffler.model.CategoryJson;
import niffler.model.CategoryValues;
import niffler.model.CurrencyValues;
import niffler.model.SpendJson;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static niffler.api.restassured.Spec.request;
import static niffler.config.NifflerConfigImpl.getUsername;
import static niffler.utilities.Utilities.randomDoubleFrom;
import static niffler.utilities.Utilities.randomEnum;

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
        SpendJson spendJson = new SpendJson()
                .setUsername(getUsername())
                .setSpendDate(new Date())
                .setCurrency(randomEnum(CurrencyValues.class))
                .setAmount(randomDoubleFrom(1.0, 50.0))
                .setDescription(new Faker().beer().name())
                .setCategory(randomEnum(CategoryValues.class).name());
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
                .post("/addSpend")
                .then()
                .statusCode(201)
                .extract().as(SpendJson.class);
    }

    @Step("GET: All Categories")
    public static List<CategoryJson> getAllCategories() {
        return request()
                .param("username", getUsername())
                .get("/categories")
                .then()
                .statusCode(200)
                .extract().as(new TypeRef<List<CategoryJson>>() {
                });
    }

    @Step("POST: Category Id: {id} for User: {username}")
    public static CategoryJson postCategory(CategoryValues name, String username) {
        return request()
                .body(new CategoryJson().setCategory(name).setUsername(username))
                .post("/category")
                .then()
                .statusCode(200)
                .extract().as(CategoryJson.class);
    }

    public static CategoryJson postCategory(CategoryValues categoryName) {
        return postCategory(categoryName, getUsername());
    }

    public static void createCategoriesApi() {
        List<CategoryValues> existingCategoriesInDbByUser = getAllCategories().stream().map(CategoryJson::getCategory).toList();
        List<CategoryValues> requiredCategories = Arrays.asList(CategoryValues.values());
        List<CategoryValues> categoriesShouldBeCreated = requiredCategories.stream().filter(category -> !existingCategoriesInDbByUser.contains(category)).toList();
        categoriesShouldBeCreated.forEach(SpendServiceApi::postCategory);
    }
}