package com.fe.blog.controller.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.CommentService;
import com.fe.blog.service.Impl.CommentServiceImpl;

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
@WebServlet(name = "SelectAllCommentCountServlet", value = "/SelectAllCommentCountServlet")
public class SelectAllCommentCountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        int blogId=Integer.parseInt(request.getParameter("blog_id"));

        CommentService cs = new CommentServiceImpl();
        int count = cs.selectCommentCount(blogId);

        ResponseData responseData = new ResponseData();
        ObjectMapper mapper = new ObjectMapper();
        if (count!=0) {
            responseData.setMsg("查询成功");
            responseData.setError("无");
            responseData.setData(count);
        } else {
            responseData.setMsg("查询失败");
            responseData.setError("原因未知！接收错误！");
            responseData.setData(null);
        }
        String resultJson = mapper.writeValueAsString(responseData);
        PrintWriter writer = response.getWriter();
        writer.print(resultJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
