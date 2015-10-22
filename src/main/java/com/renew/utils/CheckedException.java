package com.renew.utils;

import com.renew.result.ResultError;

public class CheckedException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    private ResultError result = null;
    
    public CheckedException(ResultError result,String message)
    {
        super(message);
        this.result = result;
    }
    
    public ResultError getResult()
    {
        return result;
    }
}
