package com.fe.blog.controller.fieldcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.FieldService;
import com.fe.blog.service.Impl.FIeldServiceImpl;

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
@WebServlet("/Addfield")
public class AddFieldController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        FieldService fieldService = new FIeldServiceImpl();
        String parameter = req.getParameter("Field");

        ResponseData responseData = new ResponseData();
        ObjectMapper objectMapper = new ObjectMapper();

        if(parameter != null && (fieldService.findField(parameter) == null)){
            fieldService.add(parameter);
            responseData.setMsg("添加成功");
            responseData.setError("无");
            responseData.setData(parameter);
        }
        else {
            responseData.setMsg("添加失败");
            responseData.setError("已含有该分类");
            responseData.setData(null);
        }

        String json = objectMapper.writeValueAsString(responseData);
        PrintWriter out = resp.getWriter();
        out.write(json);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
