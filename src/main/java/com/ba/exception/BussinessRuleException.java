package com.ba.exception;

import com.ba.Run;

public class BussinessRuleException extends RuntimeException {
    public BussinessRuleException(String message){
        super(message);
    }
}
