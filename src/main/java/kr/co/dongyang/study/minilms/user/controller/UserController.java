package kr.co.dongyang.study.minilms.user.controller;


import kr.co.dongyang.study.minilms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
@Controller
public class UserController {


    private final UserService userService;


    @GetMapping("/user/register")
    public String register() {
        return "user/register";

    }

    @GetMapping("/user/register-complete")
    public String registerComplete() {
        return "user/register_complete";


    }

    @GetMapping("/user/mypage")
    public String mypage(){

        return "user/mypage";
    }

    @RequestMapping("/user/login")
    public String login(){

        return  "user/login";

    }

}
