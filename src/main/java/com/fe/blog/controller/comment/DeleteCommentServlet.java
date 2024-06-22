package com.fe.blog.controller.comment;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@WebServlet(name = "DeleteCommentServlet", value = "/DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        JSONObject json = GetRequestJson.getRequestJsonObject(request);
        int id=json.getIntValue("id");

        CommentService cs = new CommentServiceImpl();

        boolean result;

        result = cs.deleted(id);

        ResponseData responseData = new ResponseData();
        ObjectMapper mapper = new ObjectMapper();

        if(result==true){
            responseData.setMsg("操作成功");
            responseData.setError("无");
            responseData.setData(null);
        }else{
            responseData.setMsg("操作失败");
            responseData.setError("原因未知，请稍后重试");
            responseData.setData(null);
        }
        String json1 = mapper.writeValueAsString(responseData);
        PrintWriter writer = response.getWriter();
        writer.print(json1);
    }
}
