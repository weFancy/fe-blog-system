package com.fe.blog.controller.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.mapper.blog.SelectBlogCount;
import com.fe.blog.utils.MybatisSession;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Fancy.we
 */
@WebServlet(name = "AllBlogCountServlet", value = "/AllBlogCountServlet")
public class AllBlogCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        SqlSession session = MybatisSession.getSession();
        SelectBlogCount selectMapper = session.getMapper(SelectBlogCount.class);
        int count = selectMapper.selectCount();
        ResponseData responseData = new ResponseData();
        ObjectMapper mapper = new ObjectMapper();

        if (count!=0) {
            responseData.setMsg("查询成功");
            responseData.setError("无");
            responseData.setData(count);
        } else {
            responseData.setMsg("查询失败");
            responseData.setError("原因未知！接收错误！");
            responseData.setData(null);
        }
        String resultJson = mapper.writeValueAsString(responseData);
        PrintWriter writer = response.getWriter();
        writer.print(resultJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
