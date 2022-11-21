package com.youkeda.comment.service.impl;

import com.youkeda.comment.dao.UserDAO;
import com.youkeda.comment.dataobject.UserDO;
import com.youkeda.comment.model.Result;
import com.youkeda.comment.model.User;
import com.youkeda.comment.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public Result<User> register(String userName, String pwd) {
        Result<User> userResult = new Result<User>();
        //  用户名为空
        if(StringUtils.isEmpty(userName)){
            userResult.setCode("600");
            userResult.setMessage("用户名不能为空");
            userResult.setSuccess(false);
            return userResult;
        }
        //  密码为空
        if(StringUtils.isEmpty(pwd)){
            userResult.setCode("601");
            userResult.setMessage("密码不能为空");
            userResult.setSuccess(false);
            return userResult;
        }
        //  用户名已经存在
        if(userDAO.findByUserName(userName) != null){
            userResult.setCode("601");
            userResult.setMessage("用户名已经存在");
            userResult.setSuccess(false);
            return userResult;
        }
        //  成功注册
        User user = new User();
        user.setUserName(userName);
        user.setGmtCreated(LocalDateTime.now());
        //  加密密码
        String saltPwd = pwd + "_ykd2020";
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();
        user.setPwd(md5Pwd);
        //  在数据库中加入该条记录
        UserDO userDO = new UserDO();
        userDO.setGmtCreated(LocalDateTime.now());
        userDO.setNickName(userName);
        userDO.setUserName(userName);
        userDO.setPwd(md5Pwd);
        userDAO.add(userDO);
        //  返回结果
        userResult.setSuccess(true);
        userResult.setData(user);
        return userResult;
    }
}
