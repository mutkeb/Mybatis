package com.youkeda;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youkeda.comment.dao.CommentDAO;
import com.youkeda.comment.model.Comment;
import com.youkeda.comment.model.Result;
import com.youkeda.comment.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class YkdTest {

    static String sessionId;
    @LocalServerPort
    int randomServerPort;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ObjectMapper mapper;

    public static void error(String msg) {
        System.err.println("<YkdError>" + msg + "</YkdError>");
    }

    @Test
    void contextLoads() throws Exception {


        Result<List<Comment>> byRefId = commentService.query("2050001");

        if (!byRefId.isSuccess()) {
            error("没有正确的处理返回数据");
            return;
        }
        Comment comment = byRefId.getData().get(0);
        long id = comment.getId();

        commentService.post("2050001",comment.getAuthor().getId(),id,"测试");

        byRefId = commentService.query("2050001");
        comment = byRefId.getData().get(0);

        if (comment.getChildren()==null || comment.getChildren().get(0)==null){
            error("没有完成树结构处理哦");
        }



    }


}
