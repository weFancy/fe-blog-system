package com.fe.blog.mapper.updateusermapper;

import com.fe.blog.bean.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Fancy.we
 */
public interface updateUserMapper {

    /**
     * 查询user表的所有信息
     */

    public User getUserByAccount(String account);

    /**
     * 修改user表的昵称，生日，简介，性别信息
     */
    public User updateUser(User user);



    /**
     *修改user的密码
     * @param account 需要修改的账户
     * @param password 修改后的密码
     */
    public User updateUserPassword(@Param("account") String account, @Param("password")String password);
}
