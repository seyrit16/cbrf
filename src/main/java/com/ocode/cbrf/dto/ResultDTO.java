package com.ocode.cbrf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO<T> {
    private String success;
    private Long status;
    protected T data;
    private String errorCode;
    private String errorMessage;

    public static ResultDTO SUCCESS_RESULT = new ResultDTO("true",0L,null, null, null);
    public static ResultDTO FAILURE_RESULT = new ResultDTO("false",0L,null, null, null);

    public static ResultDTO EMPTY_OK_RESULT= new ResultDTO("true",200L,null, null, null);
    public static ResultDTO NOT_FOUND_RESULT = new ResultDTO("false",404L,null, "404", null);
    public static ResultDTO INTERNAL_SERVER_RESULT = new ResultDTO("false",500L,null, "500", null);

    public String getSuccess() {
        if(success == null || success.isEmpty()) {
            success = Boolean.valueOf((errorCode == null || errorCode.isEmpty())
                    && (errorMessage == null || errorMessage.isEmpty())).toString();
        }
        return success;
    }

    public boolean isSuccess(){
        return Boolean.parseBoolean(getSuccess());
    }

    public boolean isFailure(){
        return !Boolean.parseBoolean(getSuccess());
    }

    public static <T> ResultDTO<T> of(T data) {
        return new ResultDTO<>("true", 0L, data, null, null);
    }

    public static <T> ResultDTO<T> of(T data, Long status) {
        return new ResultDTO<>("true", status, data, null, null);
    }
}

