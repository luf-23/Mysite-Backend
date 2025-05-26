package org.mysite.mysitebackend.Service.Impl;

import org.mysite.mysitebackend.Mapper.ArticleMapper;
import org.mysite.mysitebackend.Mapper.CategoryMapper;
import org.mysite.mysitebackend.Mapper.UserMapper;
import org.mysite.mysitebackend.Service.CommunityService;
import org.mysite.mysitebackend.entity.Article;
import org.mysite.mysitebackend.entity.Result;
import org.mysite.mysitebackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result list() {
        List<Article> articleList = articleMapper.selectArticles();
        return Result.success(articleList);
    }

    @Override
    public Result getAuthor(Integer categoryId) {
        if (categoryId == null) return Result.error("文章分类id不能为空");
        Integer authorId = categoryMapper.selectUserIdByCategoryId(categoryId);
        if (authorId == null) return Result.error("作者不存在");
        User user = userMapper.selectById(authorId);
        return Result.success(user.getUsername());
    }

    @Override
    public Result selectedList(Map<String, Object> params) {
        String title = (String) params.get("title");
        String content = (String) params.get("content");
        List<Article> articleList = articleMapper.selectArticlesByTitleAndContent(title, content);
        return Result.success(articleList);
    }
}
