package org.mysite.mysitebackend.Service.Impl;

import jakarta.servlet.http.HttpServletRequest;
import org.mysite.mysitebackend.Mapper.UserMapper;
import org.mysite.mysitebackend.Service.UserService;
import org.mysite.mysitebackend.entity.Result;
import org.mysite.mysitebackend.entity.User;
import org.mysite.mysitebackend.utils.IpLocationUtil;
import org.mysite.mysitebackend.utils.JwtUtil;
import org.mysite.mysitebackend.utils.Md5Util;
import org.mysite.mysitebackend.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IpLocationUtil ipLocationUtil;

    @Override
    public Result login(String username, String password, HttpServletRequest request) {
        if (username == null || password == null) return Result.error("用户名或密码不能为空");
        User user = userMapper.selectByUsername(username);
        if (user == null) return Result.error("用户名不存在");
        if (!Md5Util.getMD5String(password).equals(user.getPassword())) return Result.error("密码错误");
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getUserId());
        claims.put("username", user.getUsername());
        String token = JwtUtil.genToken(claims);
        System.out.println("LoginInfo:"+user);
        userMapper.updateLoginTime(user.getUserId());
        userMapper.updateLoginIp(user.getUserId(), ipLocationUtil.getAddressFromLocation(ipLocationUtil.getLocationFromIp(ipLocationUtil.getClientIP( request))));
        return Result.success(token);
    }

    @Override
    public Result register(String username, String password) {
        if (username == null || password == null) return Result.error("用户名或密码不能为空");
        User user = userMapper.selectByUsername(username);
        if (user != null) return Result.error("用户名已存在");
        userMapper.add(username, Md5Util.getMD5String(password));
        return Result.success();
    }

    @Override
    public Result getInfo() {
        Integer id = (Integer) ((Map<String,Object>)ThreadLocalUtil.get()).get("id");
        if(id== null){
            return Result.error("用户未登录");
        }
        User user = userMapper.selectById(id);
        return Result.success(user);
    }

    @Override
    public Result update(User user) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        user.setUserId((Integer) claims.get("id"));
        user.setUsername((String) claims.get("username"));
        if (user==null) return Result.error("用户信息不能为空");
        else if (user.getUserId()==null) return Result.error("用户ID不能为空");
        else if (user.getUsername()==null) return Result.error("用户名不能为空");
        userMapper.update(user);
        return Result.success();
    }

    @Override
    public Result getInfoByName(String username) {
        if (username == null) return Result.error("用户名不能为空");
        User user = userMapper.selectByUsername(username);
        return Result.success(user);
    }


}
