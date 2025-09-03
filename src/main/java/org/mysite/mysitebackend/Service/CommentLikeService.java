package org.mysite.mysitebackend.Service;

import org.mysite.mysitebackend.entity.Result;

public interface CommentLikeService {
    Result count(Integer commentId);

    Result like(Integer commentId);

    Result unLike(Integer commentId);

    Result check(Integer commentId);
}
