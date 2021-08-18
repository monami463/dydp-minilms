package kr.co.dongyang.study.minilms.user.controller;


import kr.co.dongyang.study.minilms.common.model.ResponseResult;
import kr.co.dongyang.study.minilms.common.model.ResponseResultHeader;
import kr.co.dongyang.study.minilms.user.dto.UserDto;
import kr.co.dongyang.study.minilms.user.model.ServiceResult;
import kr.co.dongyang.study.minilms.user.model.UserRegister;
import kr.co.dongyang.study.minilms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiUserController {


    private final UserService userService;


    @PostMapping("/api/user/register.api")
    public ResponseEntity<?> register(@RequestBody UserRegister parameter) {


        ServiceResult result = userService.addUser(parameter);


        if (!result.isResult()) {
            //return ResponseEntity.badRequest().body(result.getMessage());
            return ResponseResult.fail(result.getMessage());
        }

            return ResponseResult.success(parameter);



    }
    @PostMapping("/api/user/check-userid.api")
    public  ResponseEntity<?> checkUserId(@RequestBody UserRegister parameter){


        UserDto user = userService.getUser(parameter.getUserId());

        if(user !=null){

            return ResponseResult.fail("이미 존재하는 아이디 입니다.");

        }

        return ResponseResult.success(null);

    }
}







