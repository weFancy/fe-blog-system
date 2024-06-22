package com.fe.blog.service;

import com.fe.blog.bean.Blog;
import com.fe.blog.bean.Tag;

import java.util.List;

/**
 * @author Fancy.we
 */
public interface TagService {
    /**
     * 查询所有标签
     */
    List<Tag> selectALL();


    /**
     *  往库里增加一个标签
     */
    boolean add(String tag);

    /**
     *  在库里删除一个标签
     */
    boolean delete(String tag);

    /**
     * 根据标签名查询tagId
     */
    List<Tag> selectByTag(List<String> tag);

    /**
     * 查询一条博客的全部标签
     */
    List<Tag> selectTagsByBlog(int blogId);

    /**
     * 查询该标签下所有的博客信息
     */
    List<Blog> selectblogbytag(String tagname);
}
