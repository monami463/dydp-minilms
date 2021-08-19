package kr.co.dongyang.study.minilms.user.dto;

import kr.co.dongyang.study.minilms.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {


    private String userId;
    private String password;
    private String name;
    private String zipcode;
    private String addr;
    private String addrDetail;
    private String phone;
    private String gender;
    private LocalDateTime regDt;

    public String getRegDtText(){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd(HH:mm)");

        return regDt.format(dateTimeFormatter);

    }


    public static UserDto of(User user) {

        UserDto userDto = UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .zipcode(user.getZipcode())
                .addr(user.getAddr())
                .addrDetail(user.getAddrDetail())
                .phone(user.getPhone())
                .regDt(user.getRegDt())
                .build();
        return userDto;
    }

    public static List<UserDto> of(List<User> users){
        List<UserDto> userList = new ArrayList<>();
        if(users !=null){
            users.forEach(e ->{
                userList.add(UserDto.of(e));
            });
        }
        return userList;

    }

}
