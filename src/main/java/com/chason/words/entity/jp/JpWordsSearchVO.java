package com.chason.words.entity.jp;

import com.chason.words.common.PageHelper;
import lombok.Data;


/**
 * 接受页面请求模糊查询的对象
 */
@Data
public class JpWordsSearchVO {

    private String wMean;

    private String classId;

    private String groupId;

    private Integer pageSize = PageHelper.DEFAULT_PAGE_SIZE;

    private Integer pageNo = PageHelper.DEFAULT_PAGE_NUMBER;

    private Integer pageCount = 0;

}
