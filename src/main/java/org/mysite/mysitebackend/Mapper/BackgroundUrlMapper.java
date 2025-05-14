package org.mysite.mysitebackend.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BackgroundUrlMapper {

    @Select("select url from background")
    List<String> selectAll();
}
