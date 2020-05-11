package org.wxz.confserver.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/5/11 20:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeFrom {

    private String confId;

    private String content;

    private int range;

}
