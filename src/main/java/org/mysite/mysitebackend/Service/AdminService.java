package org.mysite.mysitebackend.Service;

import org.mysite.mysitebackend.entity.Announcement;
import org.mysite.mysitebackend.entity.Article;
import org.mysite.mysitebackend.entity.Result;

import java.util.List;

public interface AdminService {
    Result<List<Article>> getPendingList();

    Result<List<Article>> getPublishedList();

    Result accept(Integer articleId);

    Result reject(Integer articleId);

    Result drop(Integer articleId);

    Result<Article> getArticleDetail(Integer articleId);

    Result getUserList();

    Result<List<Announcement>> getAnnouncement();

    Result deleteAnnouncement(Integer id);

    Result addAnnouncement(Announcement announcement);
}
