package com.youkeda.comment.control;

import com.youkeda.comment.dao.UserDAO;
import com.youkeda.comment.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/users")
    @ResponseBody
    public List<UserDO> getAll() {
        return userDAO.findAll();
    }
}
