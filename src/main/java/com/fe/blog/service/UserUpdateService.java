package com.fe.blog.service;

import com.fe.blog.bean.User;

/**
 * @author Fancy.we
 */
public interface UserUpdateService {


    /**
     * 根据用户名和密码匹配数据库
     * 如果用户名和密码都正确，则成功，返回true，否则返回false
     * @param account
     * @param password
     */
    boolean judgePassword(String account,String password);

    /**
     * 根据传入的user对象修改数据库内容并返回修改后的user对象
     * @param user
     * @return User对象
     */
    User updateInfo(User user);

    /**
     * 根据传入的account获取据库内容并返回user对象
     * @param account
     * @return User对象
     */
    User getInfo(String account);

    /**
     * @param account 用户名
     * @param password 修改后的密码
     * @return
     */
    User updatePassword(String account,String password);


}
