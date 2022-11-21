package com.youkeda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YkdTest {


    public static void error(String msg) {
        System.err.println("<YkdError>" + msg + "</YkdError>");
    }


    @Test
    void contextLoads() {

        try {
            Class.forName("com.youkeda.comment.dataobject.CommentDO");
        } catch (ClassNotFoundException e) {
            error("没有创建`com.youkeda.comment.dataobject.CommentDO`类");
        }

    }

}
