package niffler.jupiter;

import org.junit.jupiter.api.extension.ExtensionContext;

import static niffler.api.restassured.SpendServiceApi.createCategoriesApi;
import static niffler.api.restassured.SpendServiceApi.postRandomSpends;

public class BeforeSuiteExtension implements AroundAllTestsExtension {
    @Override
    public void beforeAllTests(ExtensionContext context) {
        createCategoriesApi();
        postRandomSpends(5);
        System.out.println("BEFORE SUITE!");
    }


    @Override
    public void afterAllTests() {
        System.out.println("AFTER SUITE!");
    }
}
