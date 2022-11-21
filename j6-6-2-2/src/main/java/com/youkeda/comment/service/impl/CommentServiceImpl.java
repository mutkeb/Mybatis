package com.youkeda.comment.service.impl;

import com.youkeda.comment.dao.CommentDAO;
import com.youkeda.comment.dataobject.CommentDO;
import com.youkeda.comment.model.Comment;
import com.youkeda.comment.model.Result;
import com.youkeda.comment.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public Result<Comment> post(String refId, long userId, long parentId, String content) {
        Result<Comment> result = new Result<>();
        if (StringUtils.isEmpty(refId) || userId == 0 || StringUtils.isEmpty(content)) {
            result.setCode("500");
            result.setMessage("refId、userId、content 不能为空");
            return result;
        }

        String body = StringEscapeUtils.escapeHtml4(content);

        CommentDO commentDO = new CommentDO();
        commentDO.setUserId(userId);
        commentDO.setRefId(refId);
        commentDO.setParentId(parentId);
        commentDO.setContent(body);
        commentDAO.insert(commentDO);

        result.setData(commentDO.toModel());

        return result;
    }

    @Override
    public Result<List<Comment>> query(String refId) {
        //  查询到所有的评论
        List<Comment> comments = commentDAO.findByRefId(refId);
        //  构建map结构
        Map<Long, Comment> commentMap = new HashMap<>();
        //  初始化一个虚拟根节点，0可以对应一级评论的文章
        commentMap.put(0L,new Comment());
        //  把所有的评论转化为map数据
        comments.forEach(comment -> {
            commentMap.put(comment.getId(),comment);
        });
        //  再次遍历评论
        comments.forEach(comment -> {
            //  得到父评论
            Comment parent = commentMap.get(comment.getParentId());
            if(parent !=null){
                //  初始化children变量
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                // 在父评论里添加回复数据
                parent.getChildren().add(comment);
            }
        });
        //  一级评论
        List<Comment> children = commentMap.get(0L).getChildren();
        Result<List<Comment>> commentResult = new Result<>();
        commentResult.setSuccess(true);
        commentResult.setData(children);
        return commentResult;
    }


}
