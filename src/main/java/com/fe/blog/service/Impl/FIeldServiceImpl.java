package com.fe.blog.service.Impl;

import com.fe.blog.bean.Blog;
import com.fe.blog.bean.Field;

import com.fe.blog.mapper.field.FieldMapper;
import com.fe.blog.service.FieldService;
import com.fe.blog.utils.MybatisSession;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author Fancy.we
 */
public class FIeldServiceImpl implements FieldService {


    @Override
    public List<Field> selectALL() {
        SqlSession sqlSession = MybatisSession.getSession();
        FieldMapper fieldMapper = sqlSession.getMapper(FieldMapper.class);
        List<Field> allFieldTag = fieldMapper.findAllFieldTag();
        return allFieldTag;
    }

    @Override
    public boolean add(String  field) {
        SqlSession sqlSession = MybatisSession.getSession();
        FieldMapper fieldMapper = sqlSession.getMapper(FieldMapper.class);
        fieldMapper.addFieldTag(field);
        return true;
    }

    @Override
    public boolean delete(String field) {
        SqlSession sqlSession = MybatisSession.getSession();
        FieldMapper fieldMapper = sqlSession.getMapper(FieldMapper.class);
        int id = fieldMapper.selectFieldTag(field);
        fieldMapper.deleteFieldTag(id);
        return true;
    }

    @Override
    public Integer findField(String field) {
        SqlSession sqlSession = MybatisSession.getSession();
        FieldMapper fieldMapper = sqlSession.getMapper(FieldMapper.class);
        return fieldMapper.selectFieldTag(field);
    }

    @Override
    public Field selectByField(String field) {
        SqlSession sqlSession = MybatisSession.getSession();
        FieldMapper mapper = sqlSession.getMapper(FieldMapper.class);
        Field byField = mapper.selectByField(field);
        return byField;
    }

    @Override
    public List<Blog> selectBlogbyField(int fieldid,int userid,int page,int size) {
        FieldMapper mapper = MybatisSession.getSession().getMapper(FieldMapper.class);
        List<Blog> blogs = mapper.selectBlogbyField(fieldid,userid,page,size);
        return blogs;
    }

    @Override
    public List<Blog> selectBlogbytitle(int fieldid,int userid, String blogtitle,int page,int size) {
        FieldMapper mapper = MybatisSession.getSession().getMapper(FieldMapper.class);
        List<Blog> blog = mapper.selectBlogbytitle(fieldid,userid,blogtitle,page,size);
        return blog;
    }
}
