package com.fe.blog.service;

import com.fe.blog.bean.Blog;
import com.fe.blog.bean.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author Fancy.we
 */
public interface BlogService {
    /**
     * 增加博客
     * @param blog
     * @return boolean
     */
    boolean addBlog(Blog blog);

    /**
     * 删除博客
     * @param blogId
     * @return boolean
     */
    boolean deleteBlog(int blogId);

    /**
     * 修改博客
     * @param blog
     * @return boolean
     */
    boolean modifyBlog(@Param("blog") Blog blog);


    /**
     * 分页查询博客管理页面
     */
    List<Blog> selectByBlogLimit(@Param("userId") int userId, @Param("page") int page, @Param("size") int size);

    /**
     * 查询含有指定标题的博客（即模糊查询）
     * @param title
     * @return 查询的集合
     */
    List<Blog> fuzzyQuery(@Param("userId") int userId, @Param("title") String title, @Param("page") int page, @Param("size") int size);

    /**
     * 随机查询几条博客(用户默认展示的博客，如果少于用户的博客少于五条，则展示全部，多于五条则随机抽取五条展示)
     * @return 查询的集合
     */
    List<Blog> randomQuery(@Param("userId") int userId, @Param("page") int page, @Param("size") int size);

    /**
     * 在tag_relation表中建立联系
     *
     * @param blogId
     * @param tagId
     * @return
     */
    boolean addRelation(int blogId, int tagId);

    /**
     * 查询博客id
     * @param title
     * @return
     */
    Blog selectByBlogId(String title);

    /**
     * 详细查询
     * @param blogId
     * @return
     */
    Blog selectInBlog(int blogId);
    /**
     * 删除某一博客的对应关系
     *
     * @param blogId
     * @return true of false
     */
    int deleteBlogTag(int blogId);

    /**
     * 根据博客id查询全部标签
     */
    List<Tag> selectTagsByBlog(int blogId);

    List<Blog> selectDeletedBlog(@Param("userId") int userId, @Param("page") int page, @Param("size") int size);
}
