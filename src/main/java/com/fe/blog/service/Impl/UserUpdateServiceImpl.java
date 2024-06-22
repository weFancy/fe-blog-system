package com.fe.blog.service.Impl;

import com.fe.blog.bean.User;
import com.fe.blog.mapper.updateusermapper.updateUserMapper;
import com.fe.blog.service.UserUpdateService;
import com.fe.blog.utils.MybatisSession;
import org.apache.ibatis.session.SqlSession;



/**
 * @author Fancy.we
 */
public class UserUpdateServiceImpl implements UserUpdateService {

    /**
     * 判断前端传的密码，是否正确，正确才会进行修改密码的操作
     * @param account
     * @param password
     * @return
     */
    @Override
    public boolean judgePassword(String account, String password) {
        SqlSession sqlSession = MybatisSession.getSession();
        updateUserMapper mapper = sqlSession.getMapper(updateUserMapper.class);
        User user = mapper.getUserByAccount(account);
        sqlSession.close();
        if (user == null){
            return false;
        }
        if (user.getPassword().equals(password)){

            return true;
        }
        return false;
    }


    @Override
    public User updateInfo(User user) {
        SqlSession sqlSession = MybatisSession.getSession();
        updateUserMapper mapper = sqlSession.getMapper(updateUserMapper.class);
        User updateUser = mapper.updateUser(user);
        sqlSession.close();
        return updateUser;
    }

    @Override
    public User getInfo(String account) {
        SqlSession sqlSession = MybatisSession.getSession();
        updateUserMapper mapper = sqlSession.getMapper(updateUserMapper.class);
        User user = mapper.getUserByAccount(account);
        return user;
    }

    @Override
    public User updatePassword(String account, String password) {
        SqlSession sqlSession = MybatisSession.getSession();
        updateUserMapper mapper = sqlSession.getMapper(updateUserMapper.class);
        User user = mapper.updateUserPassword(account, password);
        sqlSession.close();
        return user;
    }



}
