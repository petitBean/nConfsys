package org.wxz.nconfsyscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author xingze Wang
 * @create 2020/4/29 1:58
 */
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {

    RESOURCE_TYPE_IMG(0,"IMG","图片"),
    RESOURCE_TYPE_DOC(1,"DOC","文档"),
    RESOURCE_TYPE_ZIP(2,"ZIP","压缩文件"),
    RESOURCE_TYPE_EXCL(4,"EXCL","表格"),
    RESOURCE_TYPE_ELSE(3,"ELSE","其他"),
    ;
    private int code;

    private String type;

    private String message;

}
