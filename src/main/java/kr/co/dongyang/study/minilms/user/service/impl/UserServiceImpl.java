package kr.co.dongyang.study.minilms.user.service.impl;

import kr.co.dongyang.study.minilms.admin.mapper.UserMapper;
import kr.co.dongyang.study.minilms.user.dto.UserDto;
import kr.co.dongyang.study.minilms.user.entity.User;
import kr.co.dongyang.study.minilms.user.model.ServiceResult;
import kr.co.dongyang.study.minilms.user.model.UserRegister;
import kr.co.dongyang.study.minilms.user.model.UserSearch;
import kr.co.dongyang.study.minilms.user.repository.UserRepository;
import kr.co.dongyang.study.minilms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ServiceResult addUser(UserRegister parameter) {


        if (parameter.getUserId() == null || parameter.getUserId().length() < 1) {

            return ServiceResult.fail("아이디 정보가 정확하지 않습니다.");

        }


        //사용자 아이디가 존재하는지 체크
        Optional<User> optionalUser = userRepository.findById(parameter.getUserId());
        if (optionalUser.isPresent()) {

            return ServiceResult.fail("아이디가 이미 존재합니다.");

        }


        //비밀번호를 암호화 저장해야함.
        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());


        User user = User.builder()
                .userId(parameter.getUserId())
                .password(encPassword)
                .name(parameter.getUserName())
                .zipcode(parameter.getZipcode())
                .addr(parameter.getAddr())
                .addrDetail(parameter.getAddrDetail())
                .phone(parameter.getPhone())
                .gender(parameter.getGender())
                .regDt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return ServiceResult.success();

    }

    @Override
    public UserDto getUser(String userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return null;
        }

        User user = optionalUser.get();
        return UserDto.of(user);

    }

    @Override
    public ServiceResult getUsers(UserSearch parameter) {



        int totalCount = userMapper.selectListCount(parameter);
        List<UserDto> userList = userMapper.selectList(parameter);

        //List<User> users = userRepository.findAll();
        //List<UserDto> userDtoList = UserDto.of(users);

        return ServiceResult.success(totalCount, userList);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findById(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }
        User user = optionalUser.get();

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (user.isAdminYn()) {
            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }


        return new org.springframework.security.core.userdetails.User(user.getUserId()
                , user.getPassword()
                , grantedAuthorityList);


    }
}
