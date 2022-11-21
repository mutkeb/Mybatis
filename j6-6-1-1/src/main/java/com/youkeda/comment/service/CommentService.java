package com.youkeda.comment.service;


import com.youkeda.comment.model.Comment;
import com.youkeda.comment.model.Result;

public interface CommentService {

    /**
     * @param refId     外部ID
     * @param userId    用户ID
     * @param parentId  父评论ID
     * @param content   评论内容
     * @return
     */
    public Result<Comment>  post(String refId,long userId,
                                 long parentId,String content);
}
