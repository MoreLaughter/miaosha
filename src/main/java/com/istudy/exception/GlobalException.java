package com.istudy.exception;

import com.istudy.result.CodeMsg;

public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private CodeMsg codeMsg;


    public CodeMsg getCodeMsg() {
         return codeMsg;
    }

    public GlobalException(CodeMsg codeMsg){
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

}
