package com.ocode.cbrf.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "HTTP response", examples = {"SUCCESS", "FAILURE", "EMPTY_OK", "NOT_FOUND", "INTERNAL_SERVER"})
public class ResultDTO<T> {
    @Schema(description = "Result", examples = {"true", "false"})
    private String success;

    @Schema(description = "Status", examples = {"0", "200", "404", "500"})
    private Long status;

    @Schema(description = "Data", defaultValue = "null")
    protected T data;

    @Schema(description = "Error code", examples = {"404", "500"}, defaultValue = "null")
    private String errorCode;

    @Schema(description = "Error message", defaultValue = "null")
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

