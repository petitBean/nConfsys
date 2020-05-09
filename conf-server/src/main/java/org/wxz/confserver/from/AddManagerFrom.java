package org.wxz.confserver.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xingze Wang
 * @create 2020/5/4 0:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddManagerFrom {

    private String userName;

    private String password;

    private String confId;

    private String roleName;

    private String name;

}
