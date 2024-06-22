package com.fe.blog.controller.tag;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.Impl.TagServiceImpl;
import com.fe.blog.service.TagService;
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
@WebServlet(name = "AddTagController", value = "/AddTagController")
public class AddTagController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        JSONObject json = GetRequestJson.getRequestJsonObject(request);
        String strTag = json.getString("tag");

        TagService tagService = new TagServiceImpl();
        boolean flag = tagService.add(strTag);

        ResponseData responseData = new ResponseData();
        ObjectMapper mapper = new ObjectMapper();

        if(flag==true){
            responseData.setMsg("增加标签成功");
            responseData.setError("无");
            responseData.setData(strTag);
        }else{
            responseData.setMsg("增加标签失败");
            responseData.setError("未预知的错误");
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
