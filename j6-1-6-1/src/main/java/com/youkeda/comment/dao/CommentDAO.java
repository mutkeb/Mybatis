package com.youkeda.comment.dao;

import com.youkeda.comment.dataobject.CommentDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentDAO {

    @Select("SELECT id,ref_id as refId,user_id as userId,content,parent_id as parentId,gmt_created as gmtCreated,gmt_modified as gmtModified FROM comment")
    List<CommentDO> findAll();

    @Insert("insert into comment(ref_id,user_id,content,parent_id,gmt_created,gmt_modified) values(#{refId},#{userId},#{content},#{parentId},now(),now())")
    @Options(useGeneratedKeys = true ,keyColumn = "id",keyProperty = "id")
    int insert(CommentDO commentDO);
}
