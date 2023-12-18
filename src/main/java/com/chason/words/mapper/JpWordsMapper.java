package com.chason.words.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chason.words.entity.jp.JpWords;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chason
 * @since 2023-12-11
 */
@Mapper
public interface JpWordsMapper extends BaseMapper<JpWords> {

    List<JpWords> listByLikeMap (Map<String, Object> param);

    int listByLikeMapCount (Map<String, Object> param);
}
