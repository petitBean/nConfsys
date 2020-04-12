package org.wxz.nconfsyscommon.resultVO;

import lombok.Data;

/**
 * @Author xingze Wang
 * @create 2020/4/12 16:30
 */
@Data
public class ErrorResponse {

    /**
     * 状态
     */
    private Integer code;

    private Object content;

}
