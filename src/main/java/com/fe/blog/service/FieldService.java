package com.fe.blog.service;

import com.fe.blog.bean.Blog;
import com.fe.blog.bean.Field;


import java.util.List;

/**
 * @author Fancy.we
 */
public interface FieldService {


    /**
     * 查询所有领域
     */
    List<Field> selectALL();

    /**
     *  往库里增加一个领域
     */
    boolean add(String field);

    /**
     *  在库里删除一个领域
     */
    boolean delete(String field);

    /**
     * 通过分类名称查找分类id
     */
    public Integer findField(String field);

    /**
     * 根据field查id
     */
    Field selectByField(String field);


    /**
     * 根据field名称查询所有的博客
     */
    List<Blog> selectBlogbyField(int fieldid,int userid,int page,int size);

    /**
     * 根据fileldid和blogtitle查询博客
     */
    List<Blog> selectBlogbytitle(int fieldid,int userid,String blogtitle,int page,int size);


}
