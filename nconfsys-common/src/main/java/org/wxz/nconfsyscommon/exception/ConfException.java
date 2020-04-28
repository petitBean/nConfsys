package org.wxz.nconfsyscommon.exception;

/**
 * @Author xingze Wang
 * @create 2020/4/29 1:26
 */
public class ConfException extends Exception {
    private int code;

    public ConfException(){}

    public ConfException(int code){
        this.code=code;
    }

    public ConfException(int code ,String message){
        super(message);
        this.code=code;
    }

    public ConfException(String message){
        super(message);
    }


}
