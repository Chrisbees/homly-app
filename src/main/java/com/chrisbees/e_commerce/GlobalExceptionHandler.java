package com.chrisbees.e_commerce;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerMapping;

import com.chrisbees.e_commerce.images.exception.ImageFileNotFoundException;
import com.chrisbees.e_commerce.images.exception.MimeTypeError;
import com.chrisbees.e_commerce.property.exception.PropertyNotFoundException;
import com.chrisbees.e_commerce.response.ApiError;
import com.chrisbees.e_commerce.response.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> errorMap = new HashMap<>();
        ex
                .getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    errorMap.put(error.getField(),
                            error.getDefaultMessage());
                });
        return ResponseEntity.badRequest()
        .body(ApiResponse.error(buildApiError("METHOD_ARGUMENT_ERROR", errorMap, null), request));

    }

    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<ApiResponse<ApiError>> propertyNotFound(PropertyNotFoundException ex, HttpServletRequest request) {
        String attribute = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE).toString();

        return ResponseEntity.badRequest()
        .body(ApiResponse.error(buildApiError("PROPERTY_NOT_FOUND", 
        ex.getLocalizedMessage(), null), request));

    }

    @ExceptionHandler(ImageFileNotFoundException.class)
    public ResponseEntity<ApiResponse<ApiError>> imageNotFound(ImageFileNotFoundException ex, HttpServletRequest request) {
        String attribute = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE).toString();
        return ResponseEntity.badRequest().body(ApiResponse.error(buildApiError("IMAGE_NOT_FOUND", 
        ex.getLocalizedMessage(),
         null), request));
    }

    @ExceptionHandler(MimeTypeError.class)
    public ResponseEntity<ApiResponse<ApiError>> invalidMimeType(MimeTypeError ex, HttpServletRequest request) {
        String attribute = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE).toString();
        return ResponseEntity.badRequest().body(ApiResponse.error(buildApiError("INVALID_MIME_TYPE", 
        ex.getLocalizedMessage(), null), request));
    }

    private ApiError buildApiError(String message, Object details, String traceId){
        ApiError errors = new ApiError();
        errors.setMessage(message);
        errors.setDetails(details);
        errors.setTraceId(traceId);
        return errors;
    }

}
