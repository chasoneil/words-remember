package com.chason.words.entity.jp;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author chason
 * @since 2023-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TblJpWords对象", description="")
public class JpWords implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "第几课")
    private Integer classId;

    @ApiModelProperty(value = "分组")
    private Integer groupId;

    @ApiModelProperty(value = "是否记住")
    private Integer isRem;

    @ApiModelProperty(value = "写法")
    private String wWrite;

    @ApiModelProperty(value = "读法")
    private String wRead;

    @ApiModelProperty(value = "中文含义")
    private String wMean;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "首次学习时间")
    private LocalDate firstLearnTime;

    @ApiModelProperty(value = "上次学习时间")
    private LocalDate lastLearnTime;

    @ApiModelProperty(value = "最后变更时间")
    private LocalDateTime updateTime;


}
