package kr.co.dongyang.study.minilms.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
public class UserRegister {


    String userId;
    String password;
    String userName;
    String zipcode;
    String addr;
    String addrDetail;
    String phone;
    String gender;


}
