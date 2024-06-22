package com.fe.blog.controller.mediacontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.MediaService;
import com.fe.blog.service.Impl.MediaServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Fancy.we
 */
@WebServlet("/deleteMedia")
public class DeleteMedia extends HttpServlet {
    MediaService mediaService =new MediaServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //获取前端传来媒体类型
        String type = req.getParameter("type");
        //获取前端传来需要删除的媒体文件名称集合
        ArrayList<String> deleteimages=mediaService.getParameterArrays(req.getInputStream());

        //获取存储文件位置
        String filePath =null;
        if(type.equals("image")) {filePath= this.getServletContext().getRealPath("/images/media")+File.separator;}
        else {filePath= this.getServletContext().getRealPath("/images/music")+File.separator;}
        //创建返回信息
        ResponseData responseData = null;
        if(mediaService.delete(deleteimages,type,filePath)){
            responseData=new ResponseData("添加成功",null,null);
        }
        else {
            responseData=new ResponseData("添加失败","未找到该文件，请稍后再试！",null);
        }

        ObjectMapper objectMapper =new ObjectMapper();
        String responseJson = objectMapper.writeValueAsString(responseData);
        resp.getWriter().write(responseJson);
    }
}
