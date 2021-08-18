package kr.co.dongyang.study.minilms.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminUserController {


    @GetMapping("/admin/user/list")
    public String userList(){

        return "admin/user/list";
    }
}
