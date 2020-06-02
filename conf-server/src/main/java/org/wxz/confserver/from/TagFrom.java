package org.wxz.confserver.from;

import lombok.Data;
import lombok.NonNull;

/**
 * @Author xingze Wang
 * @create 2020/5/23 22:58
 */
@Data
public class TagFrom {

    @NonNull
    private String name;

    @NonNull
    private String description;

}
