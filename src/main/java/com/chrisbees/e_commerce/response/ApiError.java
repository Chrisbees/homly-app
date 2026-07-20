package com.chrisbees.e_commerce.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private String message;

    private Object details;

    private String traceId;

    // private String timestamp;
    // private Integer status;
    // private String path;
    // private String method;
    // private ErrorMessage error;

}
