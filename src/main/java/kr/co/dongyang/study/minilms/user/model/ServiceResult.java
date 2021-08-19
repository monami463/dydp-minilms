package kr.co.dongyang.study.minilms.user.model;


import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;


@Data
public class ServiceResult<T> {

    private boolean result;
    private String message;

    private int totalCount;
    private List<T> list;
    private T detail;

    public ServiceResult() {
        this.result = false;
        this.message = "";
        this.list = null;
        this.detail = null;
    }

    public static ServiceResult fail(String message) {

        ServiceResult<Object> result = new ServiceResult<>();
        result.setMessage(message);
        return result;

    }

    public static ServiceResult success() {

        ServiceResult<Object> result = new ServiceResult<>();
        result.setResult(true);
        return result;

    }


    public static <T> ServiceResult success(T detail) {

        ServiceResult<Object> result = new ServiceResult<>();
        result.setResult(true);
        result.setDetail(detail);
        return result;

    }

    public static <S> ServiceResult success(int totalCount,List<S> list) {

        ServiceResult<S> result = new ServiceResult<>();
        result.setResult(true);
        result.setTotalCount(totalCount);
        result.setList(list);
        return result;

    }

    public static <S> ServiceResult success(List<S> list) {

        ServiceResult<S> result = new ServiceResult<>();
        result.setResult(true);
        result.setList(list);
        return result;

    }

}
