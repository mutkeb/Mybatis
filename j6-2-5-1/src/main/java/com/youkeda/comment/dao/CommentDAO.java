package com.youkeda.comment.dao;

import com.youkeda.comment.dataobject.CommentDO;
import com.youkeda.comment.dataobject.UserDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDAO {

    List<CommentDO> findAll();

    int insert(CommentDO commentDO);

    int update(CommentDO userDO);

    int delete(@Param("id") long id);

    List<CommentDO> findByRefId(@Param("refId") String refId);

}
