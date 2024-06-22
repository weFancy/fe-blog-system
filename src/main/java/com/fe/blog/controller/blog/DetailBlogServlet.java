package com.fe.blog.controller.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.Blog;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.bean.Tag;
import com.fe.blog.mapper.field.SelectFieldName;
import com.fe.blog.service.BlogService;
import com.fe.blog.service.Impl.BlogServiceImpl;
import com.fe.blog.utils.MybatisSession;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

/**
 * @author Fancy.we
 */
@WebServlet(name = "DetailBlogServlet", value = "/DetailBlogServlet")
public class DetailBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String blogId = request.getParameter("blogId");
        BlogService bs=new BlogServiceImpl();
        Blog blog = bs.selectInBlog(Integer.parseInt(blogId));
        //查询博客的所有标签
        List<Tag> taglist = bs.selectTagsByBlog(blog.getBlogId());
        //查询博客的领域
        SqlSession session = MybatisSession.getSession();
        SelectFieldName fieldMapper = session.getMapper(SelectFieldName.class);
        String field = fieldMapper.selectFieldName(blog.getFieldId());


        HashMap<String, Object> map = new HashMap<>(2);

        map.put("blog",blog);
        map.put("tags",taglist);
        map.put("field",field);
        ResponseData responseData = new ResponseData();

        if (blog != null) {
            responseData.setMsg("获取博客信息成功！");
            responseData.setError("无");
            responseData.setData(map);

        } else {
            responseData.setMsg("获取博客失败！");
            responseData.setError("该博客已删除或系统内部错误！");
            responseData.setData(null);
        }
        ObjectMapper mapper = new ObjectMapper();
        String resultJson = mapper.writeValueAsString(responseData);
        PrintWriter writer = response.getWriter();
        writer.print(resultJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
