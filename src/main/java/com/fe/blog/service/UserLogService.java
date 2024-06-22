package com.fe.blog.service;

import com.fe.blog.bean.User;


/**
 * @author Fancy.we
 */
public interface UserLogService {


    /**
     * 根据用户名和密码匹配数据库
     *
     * 1.通过用户名查询用户信息，将查询到的用户信息返回
     * 2.将用户信息中的密码，与用户输入的密码比较，一致则登陆成功，不一致则登陆失败
     *
     * @param account
     * @param password
     * @return true or false
     */
    User Login(String account,String password);

    /**
     * 根据传入的user对象修改数据库内容并返回修改后的user对象
     * @param user
     * @return User对象
     */
    User updateInfo(User user);

    /**
     * 根据传入的id获取据库内容并返回user对象
     *
     * @param id
     * @return User对象
     */
    User getInfo(int id);
}
