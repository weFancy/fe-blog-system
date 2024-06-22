package com.fe.blog.controller.blog;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.Blog;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.BlogService;
import com.fe.blog.service.Impl.BlogServiceImpl;
import com.fe.blog.utils.GetRequestJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author Fancy.we
 */
@WebServlet(name = "DeleteBlogController", value = "/DeleteBlogController")
public class DeleteBlogController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        JSONObject json = GetRequestJson.getRequestJsonObject(request);

        int blogId = json.getIntValue("blogId");
        Blog blog = new Blog();
        blog.setBlogId(blogId);

        BlogService blogService = new BlogServiceImpl();
        boolean flag = blogService.deleteBlog(blogId);

        //删除博客对应的索引标签
        blogService.deleteBlogTag(blogId);

        ResponseData responseData = new ResponseData();
        ObjectMapper mapper = new ObjectMapper();

        if(flag == true){
            responseData.setMsg("操作成功");
            responseData.setError("无");
            responseData.setData(null);
        }else{
            responseData.setMsg("操作失败");
            responseData.setError("原因未知，请稍后重试");
            responseData.setData(null);
        }
        String resultJson = mapper.writeValueAsString(responseData);
        PrintWriter writer = response.getWriter();
        writer.print(resultJson);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
