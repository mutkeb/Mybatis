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
}
