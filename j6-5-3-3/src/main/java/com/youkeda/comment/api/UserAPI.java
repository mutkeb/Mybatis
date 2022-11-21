package com.youkeda.comment.api;

import com.youkeda.comment.model.Result;
import com.youkeda.comment.model.User;
import com.youkeda.comment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author joe
 */
@Controller
public class UserAPI {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user/reg")
    @ResponseBody
    public Result<User> reg(@RequestParam(value = "userName",required = true) String userName,
                            @RequestParam(value = "pwd",required = true) String pwd) {
        return userService.register(userName, pwd);
    }

    @PostMapping("/api/user/login")
    @ResponseBody
    public Result<User> login(@RequestParam(value = "userName",required = true) String userName,
                              @RequestParam(value = "pwd",required = true) String pwd,
                              HttpServletResponse response, HttpServletRequest request) {
        Result<User> result = userService.login(userName, pwd);
        if(result.isSuccess()){
            request.getSession().setAttribute("userId",result.getData().getId());
        }
        return result;
    }
}
