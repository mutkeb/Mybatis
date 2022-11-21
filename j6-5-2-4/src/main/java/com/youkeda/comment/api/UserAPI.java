package com.youkeda.comment.api;


import com.youkeda.comment.model.Result;
import com.youkeda.comment.model.User;
import com.youkeda.comment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "api")
public class UserAPI {
    @Autowired
    private UserService userService;

    @PostMapping("user/reg")
    @ResponseBody
    public Result<User> reg(@RequestParam(value = "userName",required = true) String userName,
                            @RequestParam(value = "pwd",required = true) String pwd){
        return userService.register(userName,pwd);
    }

}
