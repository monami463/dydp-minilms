package kr.co.dongyang.study.minilms.admin.controller;

import kr.co.dongyang.study.minilms.user.dto.UserDto;
import kr.co.dongyang.study.minilms.user.model.ServiceResult;
import kr.co.dongyang.study.minilms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminUserController {

    private final UserService userService;


    @GetMapping("/admin/user/list")
    public String userList(Model model){

        ServiceResult<UserDto> result = userService.getUsers();
        if(result.isResult()){
            List<UserDto> userList = result.getList();
            model.addAttribute("userList", userList);
        }

        return "admin/user/list";
    }
}
