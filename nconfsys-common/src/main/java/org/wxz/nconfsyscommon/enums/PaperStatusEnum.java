package org.wxz.nconfsyscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author xingze Wang
 * @create 2020/5/6 15:51
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PaperStatusEnum {

    PAPER_STATUS_ENUM_NEW(0,"未评阅"),
    PAPER_STATUS_ENUM_VIEW(1,"评阅中"),
    PAPER_STATUS_ENUM_VIEWED(2,"评阅结束"),
    ;
    private int code;
    private String message;


    public static PaperStatusEnum getByCode(int code){
        for(PaperStatusEnum paperStatusEnum:PaperStatusEnum.values()){
            if(paperStatusEnum.code==code){
                return paperStatusEnum;
            }
        }
        return null;
    }

}
