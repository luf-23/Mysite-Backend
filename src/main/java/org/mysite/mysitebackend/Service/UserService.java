package org.mysite.mysitebackend.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;
import org.mysite.mysitebackend.entity.Result;
import org.mysite.mysitebackend.entity.User;

public interface UserService {
    Result login(String usernameOrEmail, String password, HttpServletRequest request);

    Result register(String username, String password,String email);

    Result getInfo();

    Result update(User user);

    Result getInfoByName(String username);

    Result captcha(String email);

    Result verifyCaptcha(String email, String captcha);
}
