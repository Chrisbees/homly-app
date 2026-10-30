package com.chrisbees.e_commerce.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter 
public enum ErrorCode {

    USER_NOT_FOUND("USER_NOT_FOUND", "User not found with id %s", HttpStatus.NOT_FOUND), 
    CHANGE_PASSWORD_MISMATCH("CHANGE_PASSWORD_MISMATCH", "Current password and new password does not match", HttpStatus.BAD_REQUEST), 
    INCORRECT_CURRENT_PASSWORD("INVALID_CURRENT_PASSWORD", "Current password is invalid", HttpStatus.BAD_REQUEST), 
    ACCOUNT_ALREADY_DEACTIVATED("ACCOUNT_ALREADY_DEACTIVATED", "Account is already deactivated", HttpStatus.BAD_REQUEST), 
    ACCOUNT_ALREADY_ENABLED("ACCOUNT_ALREADY_ENABLED", "Account is already enabled", HttpStatus.BAD_REQUEST), 
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", "Email already exists", HttpStatus.BAD_REQUEST),
    PHONE_ALREADY_EXISTS("PHONE_ALREADY_EXISTS", "Phone number already exits", HttpStatus.BAD_REQUEST), 
    PASSWORD_MISMATCH("PASSWORD_MISMATCH", "Passwords do not match", HttpStatus.BAD_REQUEST), 
    ERR_USER_DISABLED("ERR_USER_DISABLED", "User is disabled", HttpStatus.UNAUTHORIZED), 
    BAD_CREDENTIALS("BAD_CREDENTIALS", "Username/Password is incorrect", HttpStatus.UNAUTHORIZED), 
    USERNAME_NOT_FOUND("USERNAME_NOT_FOUND", "Username not found", HttpStatus.NOT_FOUND), 
    INTERNAL_EXCEPTION("INTERNAL_EXCEPTION", "Internal server error", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String defaultMessage;
    private final HttpStatus status;

    private ErrorCode(String code, String defaultMessage, HttpStatus status) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.status = status;
    }

}
