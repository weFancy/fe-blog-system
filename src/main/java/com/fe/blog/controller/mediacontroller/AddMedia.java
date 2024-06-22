package com.fe.blog.controller.mediacontroller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.Media;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.Impl.MediaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;


/**
 * @author Fancy.we
 */
@WebServlet("/addMedia")
@MultipartConfig
public class AddMedia extends HttpServlet {
    MediaServiceImpl mediaService =new MediaServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //获取前端传来媒体类型
        String type = req.getParameter("type");
        Part part=req.getPart(type);

        ObjectMapper objectMapper =new ObjectMapper();
//        //判断媒体类型
//        if(type.equals("image")){ part = req.getPart("image"); }
//        else { part = req.getPart("music"); }

        //获取存取文件夹相对路径
        String fileSavingFolder = this.getServletContext().getRealPath("/images/media");
        //获取HTTP头信息headerInfo=（form-data; name="file" filename="文件名"）
        String headerInfo = part.getHeader("content-disposition");
        //从HTTP头信息中获取文件名fileName=（文件名）
        String fileName = headerInfo.substring(mediaService.indexOfChar(headerInfo, "\"", 3)+1, mediaService.indexOfChar(headerInfo, "\"", 4));

        //判断文件名称中是否含有非法‘[’字符
        if(fileName.contains("[")){
            resp.getWriter().write(objectMapper.writeValueAsString(new ResponseData("添加失败","含有非法符号'['，请重试！",null)));
        }else {
            //判断数据库中是否存在该名称
            if(mediaService.findMedia(fileName,type)!=null){
                fileName=mediaService.createNewName(fileName);
            }
            //文件最终存储路径
            String fileSavingPath = fileSavingFolder + File.separator + fileName;
            //存储文件
            part.write(fileSavingPath);

            boolean result=false;
            if(type.equals("image")){ result=mediaService.add(new Media(1,fileName,null),type);}
            else {result=mediaService.add(new Media(1,null,fileName),type);}

            ResponseData responseData = new ResponseData();
            if(result){
                responseData.setMsg("添加成功");
                responseData.setError(null);
                responseData.setData(null);
            }else {
                responseData.setMsg("添加失败");
                responseData.setError("未找到该文件，请稍后再试！");
                responseData.setData(null);
            }
            String responseJson = objectMapper.writeValueAsString(responseData);
            resp.getWriter().write(responseJson);
        }

    }
}
