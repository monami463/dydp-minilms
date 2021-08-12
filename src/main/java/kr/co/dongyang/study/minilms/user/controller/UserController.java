package kr.co.dongyang.study.minilms.user.controller;


import kr.co.dongyang.study.minilms.user.model.ServiceResult;
import kr.co.dongyang.study.minilms.user.model.UserRegister;
import kr.co.dongyang.study.minilms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class UserController {


    private final UserService userService;


    @GetMapping("/user/register")
    public String register() {
        return "user/register";

    }

    @PostMapping("/user/register")
    public String registerSubmit(HttpServletRequest request,Model model, UserRegister parameter) {

        System.out.println(parameter.toString());


        ServiceResult result = userService.addUser(parameter);


        if (!result.isResult()) {
            model.addAttribute("errorMessage", result.getMessage());
            return "user/register";

        }
        return "redirect:/user/register-complete";
    }

    @GetMapping("/user/register-complete")
    public String registercomplete() {
        return "user/register_complete";




    }


}
