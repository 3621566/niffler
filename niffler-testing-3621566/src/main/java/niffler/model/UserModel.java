package niffler.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserModel {

    private String username;
    private String password;
    private boolean isReady = true;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
