package com.fe.blog.controller.fieldcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.Field;
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
import java.util.List;

/**
 * @author Fancy.we
 */
@WebServlet("/FindAllField")
public class FindAllFIeldController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        FieldService fieldService = new FIeldServiceImpl();
        List<Field> fields = fieldService.selectALL();

        ResponseData responseData = new ResponseData();

        if (fields != null) {
            responseData.setMsg("获取成功");
            responseData.setError("无");
            responseData.setData(fields);

        } else {
            responseData.setMsg("暂时没有分类");
            responseData.setError("不存在分类！");
            responseData.setData(null);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(responseData);
        PrintWriter out = resp.getWriter();
        out.print(json);
    }
}
