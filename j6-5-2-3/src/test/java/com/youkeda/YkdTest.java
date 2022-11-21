package com.youkeda;

import com.youkeda.comment.dao.UserDAO;
import com.youkeda.comment.dataobject.UserDO;
import com.youkeda.comment.model.Result;
import com.youkeda.comment.model.User;
import com.youkeda.comment.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class YkdTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;

    public static void error(String msg) {
        System.err.println("<YkdError>" + msg + "</YkdError>");
    }

    @Test
    void contextLoads() throws Exception {

        Result<User> register1 = userService.register(null, "123");

        if (!"600".equals(register1.getCode())) {
            error("没有判断用户名为空为null的情况");
            return;
        }

        register1 = userService.register("1", "");

        if (!"601".equals(register1.getCode())) {
            error("没有判断密码为空为null的情况");
            return;
        }

        long l = System.currentTimeMillis();
        String userName = "t" + l;
        Result<User> register = userService.register(userName, "123");

        if (!register.isSuccess()) {
            error("没有处理正确的返回结果");
            return;
        }

        UserDO userDO = userDAO.findByUserName(userName);
        if (userDO == null) {
            error("用户没有成功的插入");
            return;
        }
        String saltPwd = "123_ykd2050";
        // 生成md5值，并转为大写字母
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();
        if (!userDO.getPwd().equals(md5Pwd)) {
            error("密码没有加密");
            return;
        }

    }

}
