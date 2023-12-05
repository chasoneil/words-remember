package com.chason.test01.service.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chason.test01.entity.user.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chason
 * @since 2023-11-16
 */
public interface UserService extends IService<User> {

    Page<User> pageC(Page<User> page);

    List<User> listByLikeMap(Map<String, Object> map);

    boolean checkUser(User user);

    User getUserByNo(String no);

}
