package com.fe.blog.service.Impl;

import com.fe.blog.bean.Blog;
import com.fe.blog.bean.Tag;
import com.fe.blog.mapper.blog.BlogMapper;
import com.fe.blog.service.TagService;
import com.fe.blog.utils.MybatisSession;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author Fancy.we
 */
public class TagServiceImpl implements TagService {

    /**
     * 饿汉式单例模式
     */
    private SqlSession sqlsession = MybatisSession.getSession();
    private  BlogMapper mapper = sqlsession.getMapper(BlogMapper.class);


    /**
     *  查询所有标签
     * @return 查询的集合
     */
    @Override
    public List<Tag> selectALL() {
        List<Tag> tagList = mapper.selectTagAll();

        if (tagList != null && "".equals(tagList)){
            System.out.println(tagList);
        }

        return tagList;
    }

    /**
     *  往库里增加一个标签
     * @return 查询的集合
     */
    @Override
    public boolean add(String tag) {
        boolean flag = false;

        int row = mapper.addTag(tag);

        if (row > 0){
            flag = true;
        }
        return flag;
    }

    /**
     *  删除一个标签
     * @return 查询的集合
     */
    @Override
    public boolean delete(String tag) {
        boolean flag = false;
        int row = mapper.deleteTag(tag);

        if (row > 0) {
            flag = true;
        }

        return flag;
    }

    @Override
    public List<Tag> selectByTag(List<String> tag) {
        List<Tag> byTag =  mapper.selectByTag(tag);

        return byTag;
    }

    /**
     * 查询一条博客的全部标签
     * @param blogId
     */
    @Override
    public List<Tag> selectTagsByBlog(int blogId) {

        return null;
    }

    @Override
    public List<Blog> selectblogbytag(String tagname) {

        List<Blog> selectblogbytag = mapper.selectblogbytag(tagname);
        return selectblogbytag;
    }


}
