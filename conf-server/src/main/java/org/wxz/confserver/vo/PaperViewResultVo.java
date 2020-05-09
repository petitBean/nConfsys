package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/5/6 23:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperViewResultVo {

    private String paperId;

    private String paperAuthor;

    private String score;

    private String name;

}
