package kr.co.dongyang.study.minilms.user.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class User {
    @Id
    private String userId;
    private String password;
    private String name;
    private String zipcode;
    private String addr;
    private String addrDetail;
    private String phone;
    private String gender;
    private LocalDateTime regDt;

    private boolean adminYn; //관리자인지여부



    }

