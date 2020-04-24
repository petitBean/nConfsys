package org.wxz.nconfsyscommon.resultVO;

import lombok.Data;
import org.wxz.nconfsyscommon.enums.RestCodeEnum;

/**
 * @Author xingze Wang
 * @create 2020/4/12 16:26
 */
@Data
public class ConfResponse<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 内容
     */
    private T data;

    public ConfResponse(){
        this(RestCodeEnum.SUCCESS);
    }

    public ConfResponse(Integer code,String message,T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    /**
     * 构造
     * @param codeEnum
     */
    public   ConfResponse(RestCodeEnum codeEnum){
        this.code=codeEnum.getCode();
        this.message=codeEnum.getMessage();
        this.data=null;
    }

    public   ConfResponse(RestCodeEnum codeEnum,T data){
        this.code=codeEnum.getCode();
        this.message=codeEnum.getMessage();
        this.data=data;
    }



    /**
     * 成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ConfResponse<T> success( T data){
        ConfResponse<T> response=new ConfResponse<>(RestCodeEnum.SUCCESS);
        response.setData(data);
        return response;
    }

    /**
     * 成功
     * @param <T>
     * @return
     */
    public static <T> ConfResponse<T> success( ){
        ConfResponse<T> response=new ConfResponse<>(RestCodeEnum.SUCCESS);
        response.setData(null);
        return response;
    }

    /**
     * 失败
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ConfResponse<T> fail(T data){
        ConfResponse<T> response=new ConfResponse<>(RestCodeEnum.FAILED);
        response.setData(data);
        return response;
    }

    /**
     * 失败
     * @param <T>
     * @return
     */
    public static <T> ConfResponse<T> fail(){
        ConfResponse<T> response=new ConfResponse<>(RestCodeEnum.FAILED);
        response.setData(null);
        return response;
    }

    public static <T> ConfResponse<T> fail(String message){
        ConfResponse<T> response=new ConfResponse<>(RestCodeEnum.FAILED);
        response.setMessage(message);
        response.setData(null);
        return response;
    }


    /**
     * 失败
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ConfResponse<T> error(T data){
        ConfResponse<T> response=new ConfResponse<>(RestCodeEnum.ERROR);
        response.setData(data);
        return response;
    }

    /**
     * 失败
     * @param <T>
     * @return
     */
    public static <T> ConfResponse<T> error(){
        ConfResponse<T> response=new ConfResponse<>(RestCodeEnum.ERROR);
        response.setData(null);
        return response;
    }

    /**
     * 自己传参
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T>ConfResponse<T> newConfResponse(Integer code,String message,T data){
        return new ConfResponse<>(code,message,data);
    }




}
