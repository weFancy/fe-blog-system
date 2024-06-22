package com.fe.blog.service;

import com.fe.blog.bean.Blog;

import java.util.List;

/**
 * @author Fancy.we
 */
public interface ClassifyService {

    /**
     * 查询含有指定标签的博客
     *
     * @param tag
     * @return 查询的集合
     */
    List<Blog> selectByTag(String tag);

    /**
     * 查询指定领域的博客
     * @param field
     */
    List<Blog> selectByField(String field);
}
