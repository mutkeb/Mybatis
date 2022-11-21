package com.youkeda;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youkeda.comment.CorsConfig;
import com.youkeda.comment.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

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

        CorsConfig corsConfig = null;


    }

}
