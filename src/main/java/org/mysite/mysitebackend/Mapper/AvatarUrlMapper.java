package org.mysite.mysitebackend.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AvatarUrlMapper {

    @Select("SELECT url FROM  avatar")
    List<String> selectAll();
}
