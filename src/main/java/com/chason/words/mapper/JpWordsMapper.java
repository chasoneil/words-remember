package com.chason.words.mapper;

import com.chason.words.entity.jp.JpWords;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JpWordsMapper {

    List<JpWords> findAll();

    List<JpWords> findByClassNumber(int classNumber);

    int insert(JpWords jpWords);

    int removeById(int id);

    // int removeByClassNumber(int classNumber);

    // int updateById(int id);
}