package com.fe.blog.service.Impl;

import com.fe.blog.bean.Blog;
import com.fe.blog.bean.BlogTag;
import com.fe.blog.bean.Tag;
import com.fe.blog.mapper.blog.BlogMapper;
import com.fe.blog.mapper.blog.SelectTagByBlog;
import com.fe.blog.service.BlogService;
import com.fe.blog.utils.MybatisSession;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;


/**
 * @author Fancy.we
 */
public class BlogServiceImpl implements BlogService {


    private SqlSession sqlsession = MybatisSession.getSession();
    private BlogMapper blogMapper = sqlsession.getMapper(BlogMapper.class);

    @Override
    public boolean addBlog(Blog blog) {
        boolean flag = false;

        int row = blogMapper.addBlog(blog);
        if (row > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean deleteBlog(int blogId) {
        boolean flag = false;

        int row = blogMapper.deleteBlog(blogId);
        if (row > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean modifyBlog(Blog blog) {
        boolean flag = false;

        int row = blogMapper.updateBlog(blog);
        if (row > 0) {
            flag = true;
        }
        return flag;
    }


    @Override
    public List<Blog> selectByBlogLimit(@Param("userId") int userId, @Param("page") int page, @Param("size") int size) {
        List<Blog> blogs = blogMapper.selectByBlogLimit(userId, page, size);
        return blogs;
    }

    @Override
    public List<Blog> fuzzyQuery(@Param("userId") int userId,@Param("title") String title, @Param("page") int page, @Param("size") int size) {

        return blogMapper.fuzzyQuery(userId,title,page,size);
    }

    @Override
    public List<Blog> randomQuery(@Param("userId") int userId, @Param("page") int page, @Param("size") int size) {

        return blogMapper.randomQuery(userId,page,size);
    }

    @Override
    public boolean addRelation(int blogId, int tagId) {
        boolean flag = false;

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        BlogTag blogTag = new BlogTag();
        blogTag.setBlogId(blogId);
        blogTag.setTagId(tagId);

        int row = blogMapper.addRelation(blogTag);
        if (row > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Blog selectByBlogId(String title) {
        Blog blog = blogMapper.selectByBlogId(title);

        return blog;
    }

    @Override
    public Blog selectInBlog(int blogId) {
        Blog blog = blogMapper.selectInBlog(blogId);

        return blog;
    }
    @Override
    public int deleteBlogTag(int blogId) {
      int result = blogMapper.deleteBlogTag(blogId);
      if(result!=0){
          return result;
      }
        return 0;
    }

    /**
     * 根据博客id查询全部标签
     * @param blogId
     */
    @Override
    public List<Tag> selectTagsByBlog(int blogId) {
        SelectTagByBlog mapper = sqlsession.getMapper(SelectTagByBlog.class);

        List<Tag> tags= mapper.selectTagsByBlog(blogId);
        return tags;
    }

    @Override
    public List<Blog> selectDeletedBlog(int userId, int page, int size) {
        List<Blog> blogs = blogMapper.selectDeletedBlogLimit(userId, page, size);
        return blogs;
    }

}
