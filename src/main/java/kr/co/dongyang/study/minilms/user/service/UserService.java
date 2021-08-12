package kr.co.dongyang.study.minilms.user.service;

import kr.co.dongyang.study.minilms.user.entity.User;
import kr.co.dongyang.study.minilms.user.model.ServiceResult;
import kr.co.dongyang.study.minilms.user.model.UserRegister;

public interface UserService<user> {

    ServiceResult addUser(UserRegister parameter);






}
