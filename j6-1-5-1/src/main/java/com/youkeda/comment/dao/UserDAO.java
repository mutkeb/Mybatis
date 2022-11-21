package com.youkeda.comment.dao;

import com.youkeda.comment.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDAO {
    @Select("SELECT id,user_name as userName,pwd,nick_name as nickName,avatar,gmt_created as gmtCreated,gmt_modified as gmtModified FROM user")
    List<UserDO> findAll();
}
