package niffler.config;

import org.aeonbits.owner.ConfigFactory;

public class NifflerConfigImpl {

    private static NifflerConfig getNifflerConfig() {
        return ConfigFactory.newInstance().create(NifflerConfig.class, System.getProperties());
    }

    public static String getUsername() {
        return getNifflerConfig().username();
    }

    public static String getPassword() {
        return getNifflerConfig().password();
    }

    public static String getAppUrl() {
        return getNifflerConfig().webUrl();
    }

    public static String getApiSpendUrl() {
        return getNifflerConfig().apiUrl("spend");
    }

    public static String getApiUserDataUrl() {
        return getNifflerConfig().apiUrl("user");
    }
}