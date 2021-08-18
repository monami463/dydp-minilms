package kr.co.dongyang.study.minilms.common.model;


import lombok.Data;

@Data
public class ResponseResultHeader<T> {

    private boolean result;
    private String message;
    private String resultCode;

}
