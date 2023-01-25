package niffler.api;

import com.github.javafaker.Faker;
import niffler.model.Category;
import niffler.model.requests.CurrencyValues;
import niffler.model.requests.SpendJson;

import java.util.Date;

import static niffler.api.Spec.request;
import static niffler.config.NifflerConfigImpl.getUsername;
import static niffler.utilities.UtilityMain.randomDoubleFrom;
import static niffler.utilities.UtilityMain.randomEnum;

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
}