package com.fe.blog.controller.mediacontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.Media;
import com.fe.blog.service.MediaService;
import com.fe.blog.service.Impl.MediaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Fancy.we
 */
@WebServlet("/FindAllMedia")
public class FindAllMedia extends HttpServlet {
    MediaService mediaService = new MediaServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        String type = req.getParameter("type");
        List<Media> mediaList = mediaService.selectALL(type);
        //将查询结果转成json对象
        ObjectMapper objectMapper = new ObjectMapper();
        String resultSet = objectMapper.writeValueAsString(mediaList);
        //返回json集合
        resp.getWriter().print(resultSet);
    }
}
