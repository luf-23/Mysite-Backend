package org.mysite.mysitebackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;
import org.mysite.mysitebackend.Service.UserService;
import org.mysite.mysitebackend.entity.Result;
import org.mysite.mysitebackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestParam @Pattern(regexp = "(^\\S{5,16}$)|(^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$)") String usernameOrEmail,@RequestParam @Pattern(regexp = "^\\S{5,16}$") String password,HttpServletRequest request){
        return userService.login(usernameOrEmail, password,request);
    }
    @PostMapping("/register")
    public Result register(@RequestParam @Pattern(regexp = "^\\S{5,16}$") String username,@RequestParam @Pattern(regexp = "^\\S{5,16}$") String password,@RequestParam @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$") String email) {
        if (username.contains("@")) return Result.error("用户名不能包含@");
        return userService.register(username, password,email);
    }

    @GetMapping("/getInfo")
    public Result getInfo(){
        return userService.getInfo();
    }
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        return userService.update(user);
    }

    @GetMapping("/getInfoByName")
    public Result getInfoByName(@RequestParam @Pattern(regexp = "^\\S{5,16}$") String username){
        return userService.getInfoByName(username);
    }

    @PostMapping("/captcha")
    public Result captcha(@RequestParam String email){
        return userService.captcha(email);
    }

    @PostMapping("/verify")
    public Result verifyCaptcha(@RequestParam String email,@RequestParam String captcha){
        return userService.verifyCaptcha(email, captcha);
    }

}