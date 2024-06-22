package com.fe.blog.controller.comment;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.Comment;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.CommentService;
import com.fe.blog.service.Impl.CommentServiceImpl;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Fancy.we
 */
@WebServlet(name = "SelectAllCommentServlet", value = "/SelectAllCommentServlet")
public class SelectAllCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        //获取请求体中的JSON数据用流的方式
//        JSONObject json = GetRequestJson.getRequestJsonObject(request);
//
//        int id = json.getIntValue("id");
        //获取请求头中的数据用request.getParamter
        int blogId=Integer.parseInt(request.getParameter("blog_id"));
        int page=Integer.parseInt(request.getParameter("page"));
        int size=Integer.parseInt(request.getParameter("size"));
        System.out.println(blogId+""+page+""+size);

        CommentService cs = new CommentServiceImpl();
        List<Comment> comments = cs.selectAll(blogId,page,size);
        ResponseData responseData = new ResponseData();

        if (comments != null) {
            responseData.setMsg("获取评论成功！");
            responseData.setError("无");
            responseData.setData(comments);

        } else {
            responseData.setMsg("该博客暂时没有评论！");
            responseData.setError("不存在评论或系统内部错误！");
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
