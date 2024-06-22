package com.fe.blog.mapper.userlog;

import com.fe.blog.bean.User;

/**
 * @author Fancy.we
 */
public interface UserLogMapper {
    /**
     * 通过账号获取用户信息
     * @return user
     */
    public User getUserByAccount(String account);

}
