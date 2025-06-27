package org.mysite.mysitebackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;
import org.mysite.mysitebackend.Service.UserService;
import org.mysite.mysitebackend.entity.Result;
import org.mysite.mysitebackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestParam @Pattern(regexp = "^\\S{5,16}$") String username,@RequestParam @Pattern(regexp = "^\\S{5,16}$") String password,HttpServletRequest request){
        System.out.println("login"+username+password);
        return userService.login(username, password,request);
    }
    @PostMapping("/register")
    public Result register(@RequestParam @Pattern(regexp = "^\\S{5,16}$") String username,@RequestParam @Pattern(regexp = "^\\S{5,16}$") String password) {
        return userService.register(username, password);
    }

    @GetMapping("/getInfo")
    public Result getInfo(){
        return userService.getInfo();
    }
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        return userService.update(user);
    }

    @GetMapping("getInfoByName")
    public Result getInfoByName(@RequestParam @Pattern(regexp = "^\\S{5,16}$") String username){
        return userService.getInfoByName(username);
    }
}