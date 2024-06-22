package com.fe.blog.controller.homepagecontroller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fe.blog.bean.Homepage;

import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.HomePageService;
import com.fe.blog.service.Impl.HomePageServiceImpl;

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
@WebServlet("/HomePageManage")
public class UpdateHomePageController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String title = req.getParameter("title");
        String welcome = req.getParameter("welcome");
        String description = req.getParameter("description");
        String banner = req.getParameter("banner");
        String announcement = req.getParameter("announcement");

        Homepage homepage = new Homepage(1,title,description,welcome,banner,announcement);
        HomePageService homePageService = new HomePageServiceImpl();

        homePageService.UpdateHomePage(homepage);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseData responseData = new ResponseData();
        PrintWriter out = resp.getWriter();

        responseData.setMsg("更新成功！");
        responseData.setError("无");

        String json = objectMapper.writeValueAsString(responseData);
        out.write(json);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
