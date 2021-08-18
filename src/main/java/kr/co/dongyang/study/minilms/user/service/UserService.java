package kr.co.dongyang.study.minilms.user.service;

import kr.co.dongyang.study.minilms.user.dto.UserDto;
import kr.co.dongyang.study.minilms.user.entity.User;
import kr.co.dongyang.study.minilms.user.model.ServiceResult;
import kr.co.dongyang.study.minilms.user.model.UserRegister;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    ServiceResult addUser(UserRegister parameter);


    UserDto getUser(String userId);
}
