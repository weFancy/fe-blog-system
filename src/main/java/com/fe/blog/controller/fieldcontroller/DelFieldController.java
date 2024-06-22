package com.fe.blog.controller.fieldcontroller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.FieldService;
import com.fe.blog.service.Impl.FIeldServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Fancy.we
 */
@WebServlet("/DelField")
public class DelFieldController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        ServletInputStream inputStream = req.getInputStream();

        StringBuffer stringBuffer = new StringBuffer();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String s ="";
        while ((s = bufferedReader.readLine())!=null){
            stringBuffer.append(s);
        }

        String json =  stringBuffer.toString();
        FieldService fieldService = new FIeldServiceImpl();

        JSONArray jsonArray=JSONArray.parseArray(json);
        for(int i = 0; i<jsonArray.size(); i++){
            JSONObject object= (JSONObject) jsonArray.get(i);
            System.out.println(object.get("value"));
            fieldService.delete(object.get("value").toString());
        }
        ResponseData responseData = new ResponseData();
        ObjectMapper objectMapper = new ObjectMapper();

        if(json != null){
            responseData.setMsg("删除成功！");
            responseData.setError("无");
        }
        else{
            responseData.setMsg("删除失败");
            responseData.setError("失败");
        }

        String json1 = objectMapper.writeValueAsString(responseData);
        PrintWriter out = resp.getWriter();
        out.write(json1);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
