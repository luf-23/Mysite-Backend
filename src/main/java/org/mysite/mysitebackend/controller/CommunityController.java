package org.mysite.mysitebackend.controller;

import org.mysite.mysitebackend.Service.CommunityService;
import org.mysite.mysitebackend.entity.Article;
import org.mysite.mysitebackend.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("/list")
    public Result list(){
        return communityService.list();
    }

    @GetMapping("/author")
    public Result getAuthor(@RequestParam Integer categoryId){
        return communityService.getAuthor(categoryId);
    }

    @GetMapping("/selectedList")
    public Result selectedList(@RequestParam Map<String, Object> params){
        if (params.get("title") == null) params.put("title", "");
        if (params.get("content") == null) params.put("content", "");
        return communityService.selectedList(params);
    }
}
