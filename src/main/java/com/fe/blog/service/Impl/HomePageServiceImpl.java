package com.fe.blog.service.Impl;

import com.fe.blog.bean.Homepage;

import com.fe.blog.mapper.homepage.HomePageMapper;
import com.fe.blog.service.HomePageService;
import com.fe.blog.utils.MybatisSession;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author Fancy.we
 */
public class HomePageServiceImpl implements HomePageService {
    @Override
    public Integer UpdateHomePage(Homepage homepage) {
        SqlSession sqlSession = MybatisSession.getSession();
        HomePageMapper homePageMapper = sqlSession.getMapper(HomePageMapper.class);
        homePageMapper. UpdateHomepage(homepage);
        return 0;
    }

    @Override
    public List<Homepage> FindAllHomepage(int Homepageid) {
        SqlSession sqlSession = MybatisSession.getSession();
        HomePageMapper homePageMapper = sqlSession.getMapper(HomePageMapper.class);
        List<Homepage> homepages =homePageMapper.findHomepage(Homepageid);
        return homepages;
    }
}
