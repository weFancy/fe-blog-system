package com.fe.blog.controller.comment;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.Comment;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.CommentService;
import com.fe.blog.service.Impl.CommentServiceImpl;
import com.fe.blog.utils.GetRequestJson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;



/**
 * @author Fancy.we
 */
@WebServlet(name = "AddCommentServlet", value = "/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject json = GetRequestJson.getRequestJsonObject(request);
        int blogId = json.getIntValue("blog_id");
        String content = json.getString("content");
        int commentator = json.getIntValue("commentator");

        response.setContentType("application/json;charset=UTF-8");
        Comment comment = new Comment();
        comment.setCommentator(commentator);
        comment.setContent(content);
        comment.setBlogId(blogId);

        CommentService cs = new CommentServiceImpl();


        Comment result=null;
        result = cs.add(comment);

        ResponseData responseData = new ResponseData();
        ObjectMapper mapper = new ObjectMapper();

        if(result!=null){



            responseData.setMsg("操作成功");
            responseData.setError("无");
            responseData.setData(result);
        }else{
            responseData.setMsg("操作失败");
            responseData.setError("原因未知，请稍后重试");
            responseData.setData(null);
        }
        String resultJson = mapper.writeValueAsString(responseData);
        PrintWriter writer = response.getWriter();
        writer.print(resultJson);
    }
}
