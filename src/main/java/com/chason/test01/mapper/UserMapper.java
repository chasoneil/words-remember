package com.chason.test01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chason.test01.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chason
 * @since 2023-11-16
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    Page<User> pageC(Page<User> page);

    List<User> listByLikeMap(Map<String, Object> map);

    User getUserByNo(String no);
}
