package org.mysite.mysitebackend.Service.Impl;

import org.mysite.mysitebackend.Mapper.CommentMapper;
import org.mysite.mysitebackend.Service.CommentService;
import org.mysite.mysitebackend.entity.Comment;
import org.mysite.mysitebackend.entity.Result;
import org.mysite.mysitebackend.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Result<Map<String, Object>> list(Integer articleId, Integer page, Integer pageSize) {
        Integer offset = (page - 1) * pageSize;
        List< Comment> commentList = commentMapper.selectList(articleId, offset, pageSize);
        Integer total = commentMapper.count(articleId);
        Map<String, Object> result = Map.of("total", total, "list", commentList);
        return Result.success(result);
    }

    @Override
    public Result add(Integer articleId,String  content) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        commentMapper.add(articleId, userId, content);
        return Result.success();
    }

    @Override
    public Result delete(Integer commentId) {
        commentMapper.delete(commentId);
        return Result.success();
    }
}
