package com.chason.words.entity.jp;


import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="classInfo对象", description="")
public class ClassInfo {

    @TableId(value = "class_id")
    private int classId;

    private String name;
}
