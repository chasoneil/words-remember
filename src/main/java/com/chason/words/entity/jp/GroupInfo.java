package com.chason.words.entity.jp;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="groupInfo对象", description="")
public class GroupInfo {

    @TableId(value = "group_id")
    private int groupId;

    private String name;

}
