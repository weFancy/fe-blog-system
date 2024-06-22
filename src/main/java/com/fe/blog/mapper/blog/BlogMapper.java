package com.fe.blog.mapper.blog;

import com.fe.blog.bean.Blog;
import com.fe.blog.bean.BlogTag;
import com.fe.blog.bean.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Fancy.we
 */
public interface BlogMapper {

    /**
     * 增加博客
     */
    int addBlog(Blog blog);

    /**
     * 删除博客
     */
    int deleteBlog(int blogId);

    /**
     * 修改博客
     */
    int updateBlog(Blog blog);

    /**
     * 新增标签
     */
    int addTag(String tag);

    /**
     * 删除标签
     */
    int deleteTag(String tag);

    /**
     * 分页查询博客管理页面
     */
    List<Blog> selectByBlogLimit(@Param("userId") int userId, @Param("page") int page, @Param("size") int size);

    /**
     * 根据标题模糊查询博客
     */
    List<Blog> fuzzyQuery(@Param("userId") int userId,@Param("title") String title, @Param("page") int page, @Param("size") int size);

    /**
     * 随机查询
     */
    List<Blog> randomQuery(@Param("userId") int userId, @Param("page") int page, @Param("size") int size);


    /**
     * 查询所有标签
     */
    List<Tag> selectTagAll();

    /**
     * 绑定博客与标签
     */
    int addRelation(BlogTag blogTag);

    /**
     * 根据标签名查询tagId
     */
    List<Tag> selectByTag(List<String> tag);

    /**
     * 查询博客id
     */
    Blog selectByBlogId(String title);

    /**
     * 详细查询
     */
    Blog selectInBlog(int blogId);

    /**
     * 删除博客与标签的对应关系
     */
    int deleteBlogTag(int blogId);


    List<Blog> selectDeletedBlogLimit(@Param("userId") int userId, @Param("page") int page, @Param("size") int size);
    /**
     * 通过标签名查询到该标签下的博客信息
     */
    List<Blog> selectblogbytag(String tagname);
}
