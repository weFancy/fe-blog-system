package com.fe.blog.controller.tag;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.Impl.MediaServiceImpl;
import com.fe.blog.service.Impl.TagServiceImpl;
import com.fe.blog.service.MediaService;
import com.fe.blog.service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * @author Fancy.we
 */
@WebServlet(name = "DeleteTagController", value = "/DeleteTagController")
public class DeleteTagController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        MediaService mediaService = new MediaServiceImpl();
        TagService tagService = new TagServiceImpl();

        ArrayList<String> parameterArrays = mediaService.getParameterArrays(request.getInputStream());

        for (String tag : parameterArrays) {
            tagService.delete(tag);
        }

        ResponseData responseData = new ResponseData();
        ObjectMapper mapper = new ObjectMapper();

        if(parameterArrays != null){
            responseData.setMsg("操作成功");
            responseData.setError("无");
            responseData.setData(null);
        }else{
            responseData.setMsg("操作失败");
            responseData.setError("原因未知，请稍后重试");
            responseData.setData(null);
        }
        String json = mapper.writeValueAsString(responseData);
        PrintWriter writer = response.getWriter();
        writer.print(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
