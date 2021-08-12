package kr.co.dongyang.study.minilms.user.service.impl;

import kr.co.dongyang.study.minilms.user.entity.User;
import kr.co.dongyang.study.minilms.user.model.ServiceResult;
import kr.co.dongyang.study.minilms.user.model.UserRegister;
import kr.co.dongyang.study.minilms.user.repository.UserRepository;
import kr.co.dongyang.study.minilms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ServiceResult addUser(UserRegister parameter) {

        ServiceResult result = new ServiceResult();

        if (parameter.getUserId() ==null || parameter.getUserId().length()<1){
            result.setResult(false);
            result.setMessage("아이디 정보가 정확하지 않습니다.");
        }


        //사용자 아이디가 존재하는지 체크
        Optional<User> optionalUser = userRepository.findById(parameter.getUserId());
        if(optionalUser.isPresent()){
            result.setResult(false);
            result.setMessage("아이디가 이미 존재합니다.");
            return result;


        }


        //비밀번호를 암호화 저장해야함.
        String encPassword = parameter.getPassword();


        User user = User.builder()
                .userId(parameter.getUserId())
                .password(encPassword)
                .name(parameter.getUserName())
                .zipcode(parameter.getZipcode())
                .addr(parameter.getAddr())
                .addrDetail(parameter.getAddrDetail())
                .gender(parameter.getGender())
                .regDt(LocalDateTime.now())
                .build();

        userRepository.save(user);


        result.setResult(true);
        return result;
    }
}
