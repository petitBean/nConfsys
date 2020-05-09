package org.wxz.confserver.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/4/29 17:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationFrom {

    private String userName;

    private String confId;

    private String reason;

}
