package org.mysite.mysitebackend.Service;

import org.mysite.mysitebackend.entity.Article;
import org.mysite.mysitebackend.entity.Result;

import java.util.Map;

public interface CommunityService {
    Result list();

    Result getAuthor(Integer categoryId);

    Result selectedList(Map<String, Object> params);
}
