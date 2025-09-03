package org.mysite.mysitebackend.controller;

import org.mysite.mysitebackend.Service.CommentLikeService;
import org.mysite.mysitebackend.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commentLike")
public class CommentLikeController {

    @Autowired
    private CommentLikeService commentLikeService;
    @GetMapping("/count")
    public Result getCount(@RequestParam Integer commentId){
        if (commentId == null) return Result.error("参数错误");
        return commentLikeService.count(commentId);
    }

    @PostMapping("/like")
    public Result like(@RequestParam Integer commentId){
        if (commentId == null) return Result.error("参数错误");
        return commentLikeService.like(commentId);
    }
    @PostMapping("/unlike")
    public Result unLike(@RequestParam Integer commentId){
        if (commentId == null) return Result.error("参数错误");
        return commentLikeService.unLike(commentId);
    }

    @GetMapping("/check")
    public Result check(@RequestParam Integer commentId){
        if (commentId == null) return Result.error("参数错误");
        return commentLikeService.check(commentId);
    }
}
