package com.fe.blog.service;

import com.fe.blog.bean.Homepage;

import java.util.List;

/**
 * @author Fancy.we
 */
public interface HomePageService {


    /**
     * 实现对Homepage的数据更新操作
     */
    public Integer UpdateHomePage(Homepage homepage);


    /**
     * 实现对Homepage的查找工作
     */
    public List<Homepage> FindAllHomepage(int Homepageid);
}
