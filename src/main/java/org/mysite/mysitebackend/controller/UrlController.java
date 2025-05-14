package org.mysite.mysitebackend.controller;
import org.mysite.mysitebackend.Service.UrlService;
import org.mysite.mysitebackend.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/url")
public class UrlController {
    @Autowired
    private UrlService UrlService;

    @GetMapping("/avatarList")
    public Result avatarList(){
        return UrlService.getAvatarList();
    }

    @GetMapping("/backgroundList")
    public Result backgroundList(){
        return UrlService.getBackgroundList();
    }

}
