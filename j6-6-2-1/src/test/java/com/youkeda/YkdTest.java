package com.youkeda;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youkeda.comment.dao.CommentDAO;
import com.youkeda.comment.model.Comment;
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
    private CommentDAO commentDAO;
    @Autowired
    private ObjectMapper mapper;

    public static void error(String msg) {
        System.err.println("<YkdError>" + msg + "</YkdError>");
    }

    @Test
    void contextLoads() throws Exception {


        List<Comment> byRefId = commentDAO.findByRefId("2050001");

        if (byRefId.get(0).getAuthor().getNickName() == null) {
            error("没有正确的处理 author 属性哦");
        }
    }


}
