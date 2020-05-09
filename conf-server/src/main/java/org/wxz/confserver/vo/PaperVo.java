package org.wxz.confserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/5/6 23:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperVo {

   private String paperId;

   private String paperTopic;

   private String paperAuthor;

   private String viewStatus;

}
