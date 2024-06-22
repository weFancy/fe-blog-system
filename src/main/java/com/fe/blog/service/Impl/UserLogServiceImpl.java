package com.fe.blog.service.Impl;

import com.fe.blog.bean.User;
import com.fe.blog.mapper.userlog.UserLogMapper;
import com.fe.blog.service.UserLogService;
import com.fe.blog.utils.MybatisSession;
import org.apache.ibatis.session.SqlSession;



/**
 * @author Fancy.we
 */
public class UserLogServiceImpl implements UserLogService {

    @Override
    public User Login(String account, String password) {

        SqlSession sqlSession = MybatisSession.getSession();
        UserLogMapper mapper = sqlSession.getMapper(UserLogMapper.class);
        User user = mapper.getUserByAccount(account);

        sqlSession.close();

        // 当账号错误，数据库检索不到时，user为null，此时返回null
        if (user == null){
            return null;
        }
        // 当输入的密码与数据库中用户密码相匹配时,返回用户
        if (user.getPassword().equals(password) ){

            return user;
        }
        return null;
    }



    @Override
    public User updateInfo(User user) {
        return null;
    }

    @Override
    public User getInfo(int id) {
        return null;
    }
}
