package org.mysite.mysitebackend.Service.Impl;

import org.mysite.mysitebackend.Mapper.AdminMapper;
import org.mysite.mysitebackend.Mapper.AnnouncementMapper;
import org.mysite.mysitebackend.Mapper.ArticleMapper;
import org.mysite.mysitebackend.Service.AdminService;
import org.mysite.mysitebackend.entity.Announcement;
import org.mysite.mysitebackend.entity.Article;
import org.mysite.mysitebackend.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private static final String PUBLISHED = "published";
    private static final String DRAFT = "draft";
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private AnnouncementMapper announcementMapper;
    @Override
    public Result<List<Article>> getPendingList() {
        return Result.success(adminMapper.selectAllPendingArticles());
    }

    @Override
    public Result<List<Article>> getPublishedList() {
        return Result.success(adminMapper.selectAllPublishedArticles());
    }

    @Override
    public Result accept(Integer articleId) {
        adminMapper.updateArticleStatus(articleId, PUBLISHED);
        return Result.success();
    }

    @Override
    public Result reject(Integer articleId) {
        adminMapper.updateArticleStatus(articleId, DRAFT);
        return Result.success();
    }

    @Override
    public Result drop(Integer articleId) {
        adminMapper.updateArticleStatus(articleId, DRAFT);
        return Result.success();
    }

    @Override
    public Result<Article> getArticleDetail(Integer articleId) {
        return Result.success(articleMapper.selectById(articleId));
    }

    @Override
    public Result getUserList() {
        return Result.success(adminMapper.selectAllUser());
    }

    @Override
    public Result<List<Announcement>> getAnnouncement() {
        return Result.success(announcementMapper.selectAll());
    }

    @Override
    public Result deleteAnnouncement(Integer id) {
        announcementMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result addAnnouncement(Announcement announcement) {
        announcementMapper.add(announcement);
        return Result.success();
    }
}
