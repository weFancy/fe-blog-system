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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;


/**
 * @author Fancy.we
 */
@WebServlet(name = "SelectLimitBlogController", value = "/SelectLimitBlogController")
public class SelectLimitBlogController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        int userId = Integer.parseInt(request.getParameter("userId"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));

        HashMap<String, Object> allMap = new HashMap<>();

        SqlSession session = MybatisSession.getSession();
        SelectFieldName fieldMapper = session.getMapper(SelectFieldName.class);

        BlogService blogService = new BlogServiceImpl();
        List<Blog> blogs = blogService.selectByBlogLimit(userId,page,size);
        for(Blog blog:blogs){
            HashMap<String, Object> map = new HashMap<>(3);
            map.put("blog",blog);
            List<Tag> taglist = blogService.selectTagsByBlog(blog.getBlogId());
            map.put("tag",taglist);
            String field = fieldMapper.selectFieldName(blog.getFieldId());
            map.put("field",field);

            allMap.put("blogInfo"+blog.getBlogId(),map);
        }

        ResponseData responseData = new ResponseData();

        if (blogs!=null) {
            responseData.setData("获取博客列表成功！");
            responseData.setError("无");
            responseData.setData(allMap);
        } else {
            responseData.setMsg("暂时没有博客");
            responseData.setError("不存在博客或系统内部错误");
            responseData.setData(null);
        }
        ObjectMapper mapper = new ObjectMapper();
        String resultJson = mapper.writeValueAsString(responseData);
        PrintWriter writer = response.getWriter();
        writer.print(resultJson);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
