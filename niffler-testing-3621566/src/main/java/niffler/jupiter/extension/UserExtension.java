package niffler.jupiter.extension;

import io.qameta.allure.AllureId;
import niffler.jupiter.annotation.User;
import niffler.model.UserModel;
import niffler.model.UserType;
import org.junit.jupiter.api.extension.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.codeborne.selenide.Selenide.sleep;
import static niffler.model.UserType.*;

public class UserExtension implements BeforeTestExecutionCallback, ParameterResolver, AfterTestExecutionCallback {
    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(UserExtension.class);
    private static final Map<UserType, Queue<UserModel>> USERS_QUEUE = new ConcurrentHashMap<>();
    private static final int GET_USER_TIMEOUT_MINUTES = 1;

    static {
        Queue<UserModel> adminUsers = new LinkedList<>();
        adminUsers.add(new UserModel("admin_1", "admin_password_1"));
        adminUsers.add(new UserModel("admin_2", "admin_password_2"));
        adminUsers.add(new UserModel("admin_3", "admin_password_3"));
        USERS_QUEUE.put(ADMIN, adminUsers);

        Queue<UserModel> managerUsers = new LinkedList<>();
        managerUsers.add(new UserModel("manager_1", "manager_password_1"));
        managerUsers.add(new UserModel("manager_2", "manager_password_2"));
        managerUsers.add(new UserModel("manager_3", "manager_password_3"));
        USERS_QUEUE.put(MANAGER, managerUsers);

        Queue<UserModel> commonUsers = new LinkedList<>();
        commonUsers.add(new UserModel("common_1", "common_password_1"));
        commonUsers.add(new UserModel("common_2", "common_password_2"));
        commonUsers.add(new UserModel("common_3", "common_password_3"));
        USERS_QUEUE.put(COMMON, commonUsers);
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) {
        List<UserType> userTypes = Arrays.stream(extensionContext.getRequiredTestMethod().getParameters())
                .filter(p -> p.isAnnotationPresent(User.class))
                .filter(p -> p.getType().isAssignableFrom(UserModel.class))
                .map(p -> p.getAnnotation(User.class).value())
                .toList();

        Map<UserType, List<UserModel>> users = new HashMap<>();
        userTypes.forEach(type -> users.put(type, new ArrayList<>()));

        userTypes.forEach(type -> users.get(type).add(pollUserFromQueue(type)));

        extensionContext.getStore(NAMESPACE).put(getTestIdentifier(extensionContext), users);
    }

    private UserModel pollUserFromQueue(UserType type) {
        if (!USERS_QUEUE.containsKey(type))
            throw new RuntimeException("There are no users with role " + type);

        Queue<UserModel> queue = USERS_QUEUE.get(type);
        for (int i = 1; i <= GET_USER_TIMEOUT_MINUTES * 2; i++) {
            UserModel user = queue.poll();
            if (user != null)
                return user;
            sleep(1_000);
        }
        throw new RuntimeException("No user found with role " + type);
    }

    private String getTestIdentifier(ExtensionContext extensionContext) {
        return extensionContext.getRequiredTestMethod().getAnnotation(AllureId.class) + "_" +
                extensionContext.getDisplayName();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        String testIdentifier = getTestIdentifier(context);

        Map<UserType, List<UserModel>> usersMap = context.getStore(NAMESPACE).get(testIdentifier, Map.class);
        for (UserType type : usersMap.keySet()) {
            usersMap.get(type).forEach(user -> {
                user.setReady(true);
                USERS_QUEUE.get(type).add(user);
            });
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(UserType.class)
                && parameterContext.getParameter().isAnnotationPresent(User.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        String testIdentifier = getTestIdentifier(extensionContext);
        UserType type = parameterContext.getParameter().getAnnotation(User.class).value();

        Map<UserType, List<UserModel>> users = extensionContext.getStore(NAMESPACE).get(testIdentifier, Map.class);
        for (UserModel user : users.get(type)) {
            if (!user.isReady())
                continue;
            user.setReady(false);
            return user;
        }
        throw new RuntimeException("No user found with type " + type);
    }
}
