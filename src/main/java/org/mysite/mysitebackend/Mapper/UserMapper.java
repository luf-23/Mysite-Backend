package org.mysite.mysitebackend.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mysite.mysitebackend.entity.User;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

    @Insert("insert into user (username, password,nickname) values (#{username}, #{password},'匿名用户')")
    void add(String username, String password);

    @Select("select * from user where user_id=#{id}")
    User selectById(int id);
    @Update( "update user set nickname=#{nickname}, signature=#{signature},avatar_image=#{avatarImage},background_image=#{backgroundImage},update_time=now() where user_id=#{userId} and username=#{username}")
    void update(User user);

    @Update("update user set last_login=now() where user_id=#{userId}")
    void updateLoginTime(Integer userId);
}
