package org.mysite.mysitebackend.Service;

import jakarta.validation.constraints.Pattern;
import org.mysite.mysitebackend.entity.Result;
import org.mysite.mysitebackend.entity.User;

public interface UserService {
    Result login(String username, String password);

    Result register(String username, String password);

    Result getInfo();

    Result update(User user);

    Result getInfoByName(String username);
}
