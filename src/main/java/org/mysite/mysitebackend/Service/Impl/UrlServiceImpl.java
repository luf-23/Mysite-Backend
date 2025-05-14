package org.mysite.mysitebackend.Service.Impl;

import org.mysite.mysitebackend.Mapper.AvatarUrlMapper;
import org.mysite.mysitebackend.Mapper.BackgroundUrlMapper;
import org.mysite.mysitebackend.Service.UrlService;
import org.mysite.mysitebackend.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private AvatarUrlMapper avatarUrlMapper;
    @Autowired
    private BackgroundUrlMapper backgroundUrlMapper;
    @Override
    public Result getAvatarList() {
        List<String> avatarList = avatarUrlMapper.selectAll();
        return Result.success(avatarList);
    }

    @Override
    public Result getBackgroundList() {
        List<String> backgroundList = backgroundUrlMapper.selectAll();
        return Result.success(backgroundList);
    }
}
