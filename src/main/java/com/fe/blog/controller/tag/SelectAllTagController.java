package com.fe.blog.controller.tag;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.bean.Tag;
import com.fe.blog.service.Impl.TagServiceImpl;
import com.fe.blog.service.TagService;

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
@WebServlet(name = "SelectAllTagController", value = "/SelectAllTagController")
public class SelectAllTagController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        TagService tagService = new TagServiceImpl();
        List<Tag> tagList = tagService.selectALL();

        ResponseData responseData = new ResponseData();

        if (tagList != null) {
            responseData.setMsg("获取成功");
            responseData.setError("无");
            responseData.setData(tagList);

        } else {
            responseData.setMsg("暂时没有标签");
            responseData.setError("不存在标签！");
            responseData.setData(null);
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseData);
        PrintWriter writer = response.getWriter();
        writer.print(json);
    }
}
