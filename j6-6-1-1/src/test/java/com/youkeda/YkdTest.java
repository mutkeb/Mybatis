package com.youkeda;

import com.youkeda.comment.model.Comment;
import com.youkeda.comment.model.Result;
import com.youkeda.comment.model.User;
import com.youkeda.comment.service.CommentService;
import com.youkeda.comment.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class YkdTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    public static void error(String msg) {
        System.err.println("<YkdError>" + msg + "</YkdError>");
    }

    @Test
    void contextLoads() throws Exception {




        Result<Comment> post = commentService.post("2050001", 0, 0, "<bread> 测试");

        if (!post.isSuccess() && !post.getCode().equals("500")){
            error("没有正确的处理参数，请完善哦");
            return;
        }

        Result<User> demo = userService.register("demo", "123");
        long userId = 0;
        if (demo.isSuccess()) {
            userId = demo.getData().getId();
        } else {
            userId = userService.login("demo", "123").getData().getId();
        }

        post = commentService.post("2050001", userId, 0, "<bread> 测试");
        
        if (!StringUtils.equals("&lt;bread&gt; 测试",post.getData().getContent())){
            error("没有正确的处理 HTML 转义哦");
        }


//        File file = new File("src/main/java/com/youkeda/comment/service/impl/UserServiceImpl.java");
//        String text = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
//
//        if (text.contains("setId")) {
//            error("没有正确的重构 UserServiceImpl 类哦");
//        }
//
//        CommentDO commentDO = new CommentDO();
//        commentDO.setUserId(123);
//        Comment comment = commentDO.toModel();
//
//        if (comment.getAuthor() == null || comment.getAuthor().getId() != 123) {
//            error("CommentDO.toModel 方法没有正确的处理 author 属性");
//        }

    }

}
