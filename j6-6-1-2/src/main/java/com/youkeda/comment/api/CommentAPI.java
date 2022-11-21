package com.youkeda.comment.api;


import com.youkeda.comment.model.Comment;
import com.youkeda.comment.model.Result;
import com.youkeda.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentAPI {
    @Autowired
    private CommentService commentService;

    @PostMapping("/api/comment/post")
    @ResponseBody
    public Result<Comment> post(@RequestParam(value = "refId",required = true) String refId,
                                @RequestParam(value = "parentId",required = true) long parentId,
                                @RequestParam(value = "content",required = true) String content,HttpServletRequest request){
        Long userId  = (Long) request.getSession().getAttribute("userId");
        return commentService.post(refId,userId,parentId,content);
    }
}
