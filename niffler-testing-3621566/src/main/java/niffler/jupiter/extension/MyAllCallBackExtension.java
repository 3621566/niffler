package niffler.jupiter.extension;

import org.junit.jupiter.api.extension.*;

import static niffler.pages.BasePage.allureLog;

public class MyAllCallBackExtension implements AfterAllCallback,
        AfterEachCallback,
        BeforeAllCallback,
        BeforeEachCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback {

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        String methodName = "### AfterAllCallback ";
        checkLifeCycle(context, methodName);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        String methodName = "      ### BeforeEachCallback";
        checkLifeCycle(context, methodName);
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        String methodName = "### BeforeAllCallback";
        checkLifeCycle(context, methodName);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        String methodName = "      ### AfterEachCallback";
        checkLifeCycle(context, methodName);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        String methodName = "               ### AfterTestExecutionCallback";
        checkLifeCycle(context, methodName);
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        String methodName = "              ### BeforeTestExecutionCallback";
        checkLifeCycle(context, methodName);
    }

    private static void checkLifeCycle(ExtensionContext context, String methodName) {
        System.out.println(methodName);
        allureLog(methodName);
        try {
            System.out.println(methodName + " Required Test Method Passed: " + context.getRequiredTestMethod());
            allureLog(methodName + " Required Test Method Passed: " + context.getRequiredTestMethod());
        } catch (Exception e) {
            System.out.println(methodName + " Required Test Method Failed: " + e);
        }

        try {
            System.out.println(methodName + " Required Test Class Passed: " + context.getRequiredTestClass());
            allureLog(methodName + " Required Test Class Passed: " + context.getRequiredTestClass());
        } catch (Exception e) {
            System.out.println(methodName + " Required Test Class Failed: " + e);
            allureLog(methodName + " Required Test Class Failed: " + e);
        }

        try {
            System.out.println(methodName + " Required Test Instance Passed: " + context.getRequiredTestInstance());
            allureLog(methodName + " Required Test Instance Passed: " + context.getRequiredTestInstance());
        } catch (Exception e) {
            System.out.println(methodName + " Required Test Instance Failed: " + e);
            allureLog(methodName + " Required Test Instance Failed: " + e);
        }
    }
}
