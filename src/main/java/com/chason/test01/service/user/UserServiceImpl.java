package com.chason.test01.service.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chason.test01.entity.user.User;
import com.chason.test01.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chason.test01.utils.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chason
 * @since 2023-11-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> pageC(Page<User> page) {
        return userMapper.pageC(page);
    }

    @Override
    public List<User> listByLikeMap(Map<String, Object> map) {
        return userMapper.listByLikeMap(map);
    }

    /**
     * 用户登录检查用户账号和密码
     * @param user
     * @return
     */
    @Override
    public boolean checkUser (User user) {

        User storageUser = userMapper.getUserByNo(user.getNo());
        if (storageUser == null) {
            return false;
        }
        String rightPwd = SecretUtil.password(storageUser.getNo(), storageUser.getPassword());
        if (rightPwd.equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByNo (String no) {
        return userMapper.getUserByNo(no);
    }


}
