package com.chason.words.common;

import lombok.Data;

import java.util.Map;

@Data
public class PageHelper {

    public static final int DEFAULT_PAGE_SIZE = 10;

    public static final int DEFAULT_PAGE_NUMBER = 1;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private int pageNumber = DEFAULT_PAGE_NUMBER;

    private Map<String, Object> param;


}
