package kr.co.dongyang.study.minilms.admin.controller;

import kr.co.dongyang.study.minilms.user.dto.UserDto;
import kr.co.dongyang.study.minilms.user.model.ServiceResult;
import kr.co.dongyang.study.minilms.user.model.UserSearch;
import kr.co.dongyang.study.minilms.user.service.UserService;
import kr.co.dongyang.study.minilms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminUserController {

    private final UserService userService;


    @GetMapping("/admin/user/list")
    public String userList(Model model, HttpServletRequest request
            , UserSearch parameter){



        ServiceResult<UserDto> result = userService.getUsers(parameter);
        if(result.isResult()){
            int totalCount = result.getTotalCount();
            List<UserDto> userList = result.getList();
            model.addAttribute("totalCount", totalCount);
            model.addAttribute("userList", userList);



            PageUtil pageUtil = new PageUtil(totalCount, parameter.getPageIndex(), parameter.getQueryString());
            String pagerHtml = pageUtil.pager();

            model.addAttribute("pager", pagerHtml);

        }

        return "admin/user/list";
    }
}
