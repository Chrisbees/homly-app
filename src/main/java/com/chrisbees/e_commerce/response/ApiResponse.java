package com.chrisbees.e_commerce.response;

import java.time.LocalDateTime;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private String path;
    private String method;
    private String timestamp;
    private T data;
    private ApiError error;
    


    public static <T> ApiResponse <T> error (ApiError error, HttpServletRequest request){

        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setPath(request.getRequestURI());
        response.setMethod(request.getMethod());
        response.setTimestamp(LocalDateTime.now().toString());
        response.setError(error);
        return response;

    } 

    public static <T> ApiResponse<T> success(T data, String message, HttpServletRequest request) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setPath(request.getRequestURI());
        response.setMethod(request.getMethod());
        response.setTimestamp(LocalDateTime.now().toString());
        response.setData(data);
        return response;
    }
    
}
