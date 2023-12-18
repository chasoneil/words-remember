package com.chason.words.entity.jp;


import lombok.Data;

import java.util.List;

/**
 * 返回页面展示数据的对象
 */
@Data
public class JpWordsPageVO {

    private List<JpWords> data;

    private int count;

}
