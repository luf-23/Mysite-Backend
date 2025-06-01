package org.mysite.mysitebackend.controller;

import org.mysite.mysitebackend.Mapper.ArticleMapper;
import org.mysite.mysitebackend.Service.AdminService;
import org.mysite.mysitebackend.entity.Article;
import org.mysite.mysitebackend.entity.Result;
import org.mysite.mysitebackend.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ArticleMapper articleMapper;
    @GetMapping("/pendingList")
    public Result<List<Article>> getPendingList() {
        if (!isAdmin()) return Result.error("权限不足");
        return adminService.getPendingList();
    }

    @GetMapping("/publishedList")
    public Result<List<Article>> getPublishedList(){
        if (!isAdmin()) return Result.error("权限不足");
        return adminService.getPublishedList();
    }

    @GetMapping("/articleDetail")
    public Result<Article> getArticleDetail(Integer articleId){
        if (!isAdmin()) return Result.error("权限不足");
        if (articleMapper.selectById(articleId) == null) return Result.error("文章不存在");
        return adminService.getArticleDetail(articleId);
    }

    @PostMapping("/accept")
    public Result accept(Integer articleId){
        if (!isAdmin()) return Result.error("权限不足");
        if (articleMapper.selectById(articleId) == null) return Result.error("文章不存在");
        return adminService.accept(articleId);
    }

    @PostMapping("/reject")
    public Result reject(Integer articleId){
        if (!isAdmin()) return Result.error("权限不足");
        if (articleMapper.selectById(articleId) == null) return Result.error("文章不存在");
        return adminService.reject(articleId);
    }

    @PostMapping("/drop")
    public Result drop(Integer articleId){
        if (!isAdmin()) return Result.error("权限不足");
        if (articleMapper.selectById(articleId) == null) return Result.error("文章不存在");
        return adminService.drop(articleId);
    }

    @GetMapping("/users")
    public Result UserList(){
        if (!isAdmin()) return Result.error("权限不足");
        return adminService.getUserList();
    }

    private boolean isAdmin(){
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        if (!username.equals("admin")) return false;
        return true;
    }


}
