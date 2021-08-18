package kr.co.dongyang.study.minilms.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
public class UserRegister {


    private String userId;
    private String password;
    private String userName;
    private String zipcode;
    private String addr;
    private String addrDetail;
    private String phone;
    private String gender;


}
