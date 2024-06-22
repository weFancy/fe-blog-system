package com.fe.blog.controller.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.Blog;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.BlogService;
import com.fe.blog.service.Impl.BlogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Fancy.we
 */
@WebServlet(name = "FuzzyQueryBlog", value = "/FuzzyQueryBlog")
public class FuzzyQueryBlog extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        int userId = Integer.parseInt(request.getParameter("userId"));
        String title = request.getParameter("title");
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));

        BlogService blogService = new BlogServiceImpl();
        List<Blog> blogs = blogService.fuzzyQuery(userId,title,page,size);
        ResponseData responseData = new ResponseData();

        if (blogs != null) {
            responseData.setMsg("搜索成功！");
            responseData.setError("无");
            responseData.setData(blogs);

        } else {
            responseData.setMsg("暂时没有该博客！");
            responseData.setError("不存在该标题博客！");
            responseData.setData(null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseData);
        PrintWriter writer = response.getWriter();
        writer.print(json);
    }
}
