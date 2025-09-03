package org.mysite.mysitebackend.Service.Impl;

import org.apache.ibatis.annotations.Mapper;
import org.mysite.mysitebackend.Mapper.CommentLikeMapper;
import org.mysite.mysitebackend.Service.CommentLikeService;
import org.mysite.mysitebackend.entity.Result;
import org.mysite.mysitebackend.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {

    @Autowired
    private CommentLikeMapper commentLikeMapper;

    @Override
    public Result count(Integer commentId) {
        return Result.success(commentLikeMapper.count(commentId));
    }

    @Override
    public Result like(Integer commentId) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        if (commentLikeMapper.selectByCommentIdAndUserId(commentId, userId) != null) {
            return Result.error("已经点过赞了");
        }
        commentLikeMapper.add(commentId, userId);
        return Result.success();
    }

    @Override
    public Result unLike(Integer commentId) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        if (commentLikeMapper.selectByCommentIdAndUserId(commentId, userId) == null) {
            return Result.error("还没有点赞");
        }
        commentLikeMapper.delete(commentId, userId);
        return Result.success();
    }

    @Override
    public Result check(Integer commentId) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        return Result.success(commentLikeMapper.selectByCommentIdAndUserId(commentId, userId) != null);
    }
}
