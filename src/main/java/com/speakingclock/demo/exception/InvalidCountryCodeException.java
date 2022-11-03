package com.speakingclock.demo.exception;

import lombok.Getter;

@Getter
public class InvalidCountryCodeException extends RuntimeException {
    private String errorMsg;
    public InvalidCountryCodeException(String errorMsg){
        super(errorMsg);
        this.errorMsg=errorMsg;
    }
}
