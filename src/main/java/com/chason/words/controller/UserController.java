package com.chason.words.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chason.words.common.PageHelper;
import com.chason.words.common.R;
import com.chason.words.common.error.ErrorCode;
import com.chason.words.entity.user.User;
import com.chason.words.entity.user.UserSearchVO;
import com.chason.words.service.user.UserService;
import com.chason.words.utils.SecretUtil;
import com.chason.words.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chason
 * @since 2023-11-16
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * list all user
     * @return
     */
    @GetMapping("/listAll")
    public R listAll() {
        return R.ok(userService.list());
    }

    @PostMapping("/list")
    public R listByCondition (@RequestBody UserSearchVO user) {
        Map<String, Object> param = new HashMap<>();
        param.put("name", user.getUserName());
        return R.ok(userService.listByLikeMap(param));
    }

    /**
     * save user (one user)
     * @param user
     * @return
     */
    @PostMapping("/add")
    public R save (@RequestBody User user) {
        user.setPassword(SecretUtil.encrypt(user.getNo(), user.getPassword()));
        return userService.save(user) ? R.ok() : R.error();
    }

    /**
     * modify user by id
     * @param user
     * @return
     */
    @PostMapping("/modify")
    public R modify (@RequestBody User user) {
        userService.updateById(user);
        return R.ok();
    }

    /**
     * save or update user
     * @param user
     * @return
     */
    @PostMapping("/update")
    public R saveOrModify (@RequestBody User user) {

        User saveUser = userService.getById(user.getId());

        if (user.getIsValid() == 0) {
            return R.error(ErrorCode.USER_FRONZEN, "用户冻结");
        }

        saveUser = updateUser(saveUser, user);

        boolean updateRes = false;
        String errMsg = "";
        try {
            updateRes = userService.saveOrUpdate(saveUser);
        } catch (Exception e) {
            errMsg = e.getMessage();
        }

        return  updateRes ? R.ok() : R.error(ErrorCode.UPDATE_USER_ERROR, errMsg);
    }

    /**
     * delete user
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public R delete (@PathVariable Integer id) {
        return userService.removeById(id) ? R.ok() : R.error();
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public R login (@RequestBody User user) {

        return userService.checkUser(user) ? R.ok(userService.getUserByNo(user.getNo()))
                : R.error(ErrorCode.USER_LOGIN_FAIL, "用户信息校验失败");
    }

    /**
     * 使用 MybatisPlus 的分页插件
     * @param pageHelper
     * @return
     */
    @PostMapping("/listPage")
    public List<User> list (@RequestBody PageHelper pageHelper) {
        Page<User> page = new Page<>(pageHelper.getPageNumber(), pageHelper.getPageSize());
        Page<User> result = userService.page(page);
        return result.getRecords();
    }

    /**
     * 自定义分页  使用自己的SQL语句
     * @param pageHelper
     * @return
     */
    @PostMapping("/listPageC")
    public List<User> listC (@RequestBody PageHelper pageHelper) {

        Page<User> page = new Page<>(pageHelper.getPageNumber(), pageHelper.getPageSize());
        Page<User> pages = userService.pageC(page);
        return pages.getRecords();
    }

    public static User updateUser (User oriUser, User tarUser) {

        if (!StringUtil.isEmpty(tarUser.getName())) {
            oriUser.setName(tarUser.getName());
        }

        if (!StringUtil.isEmpty(tarUser.getPhone())) {
            oriUser.setPhone(tarUser.getPhone());
        }

        if (tarUser.getAge() != 0) {
            oriUser.setAge(tarUser.getAge());
        }

        oriUser.setSex(tarUser.getSex());
        oriUser.setRoleId(tarUser.getRoleId());

        return oriUser;
    }


}
