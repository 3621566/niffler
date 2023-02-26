package niffler.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.*;

@LoadPolicy(LoadType.MERGE)
@Sources({"system:properties",
        "classpath:user.properties",
        "classpath:remote.properties"})
public interface NifflerConfig extends Config {
    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("web.url")
    String webUrl();

    @Key("api.url")
    String apiUrl();
}