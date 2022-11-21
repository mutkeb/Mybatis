package com.youkeda.comment.dao;

import com.youkeda.comment.dataobject.CommentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentDAO {



    @Select("SELECT id,ref_id as refId,user_id as userId,content,parent_id as parentId,gmt_created as gmtCreated,gmt_modified as gmtModified FROM comment")
    List<CommentDO> findAll();

}
