package org.wxz.confserver.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/5/9 0:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentFrom {

    private String userName;

    private String comment;

    private String paperId;

    private double score;

}
