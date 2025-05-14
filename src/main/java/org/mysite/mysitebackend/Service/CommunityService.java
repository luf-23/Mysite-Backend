package org.mysite.mysitebackend.Service;

import org.mysite.mysitebackend.entity.Article;
import org.mysite.mysitebackend.entity.Result;

public interface CommunityService {
    Result list();

    Result getAuthor(Integer categoryId);
}
