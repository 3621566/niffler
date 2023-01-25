package niffler.jupiter;

import org.junit.jupiter.api.extension.ExtensionContext;

import static niffler.api.ApiRequests.postSpends;

public class BeforeSuiteExtension implements AroundAllTestsExtension {
    @Override
    public void beforeAllTests(ExtensionContext context) {
        postSpends();
        System.out.println("BEFORE SUITE!");
    }

    @Override
    public void afterAllTests() {
        System.out.println("AFTER SUITE!");
    }
}
