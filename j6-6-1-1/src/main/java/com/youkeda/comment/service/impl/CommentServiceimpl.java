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

import java.time.LocalDateTime;

@Component
public class CommentServiceimpl implements CommentService {
    @Autowired
    private CommentDAO commentDAO;


    @Override
    public Result<Comment> post(String refId, long userId, long parentId, String content) {
        Result<Comment> commentResult = new Result<>();
        //  参数为空
        if(StringUtils.isEmpty(refId) || userId == 0 || StringUtils.isEmpty(content)){
            commentResult.setSuccess(false);
            commentResult.setMessage("refId、userId、content不能为空");
            commentResult.setCode("500");
            return commentResult;
        }
        //  对content进行安全处理
        String body = StringEscapeUtils.escapeHtml4(content);
        //  插入数据
        CommentDO commentDO = new CommentDO();
        commentDO.setContent(body);
        commentDO.setGmtCreated(LocalDateTime.now());
        commentDO.setRefId(refId);
        commentDO.setUserId(userId);
        commentDO.setParentId(parentId);
        commentDAO.insert(commentDO);
        //  模型转换
        Comment comment = commentDO.toModel();
        commentResult.setSuccess(true);
        commentResult.setData(comment);
        return commentResult;
    }
}
