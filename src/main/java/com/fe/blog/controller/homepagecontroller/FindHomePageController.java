package com.fe.blog.controller.homepagecontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.Homepage;
import com.fe.blog.service.HomePageService;
import com.fe.blog.service.Impl.HomePageServiceImpl;

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
@WebServlet("/FindHomePage")
public class FindHomePageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        HomePageService homePageService = new HomePageServiceImpl();
        List<Homepage> homepages = homePageService.FindAllHomepage(1);
        String json = objectMapper.writeValueAsString(homepages);
        PrintWriter out = resp.getWriter();
        out.print(json);
    }
}
