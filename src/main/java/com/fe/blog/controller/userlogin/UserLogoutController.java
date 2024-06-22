package com.fe.blog.controller.userlogin;

import com.fe.blog.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author Fancy.we
 */
@WebServlet(name = "UserLogoutController",urlPatterns = "/UserLogoutController")
public class UserLogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //false代表：不创建session对象，只是从request中获取。
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        //清除session的属性, 跳到起始页
        session.removeAttribute("user");

        PrintWriter out = resp.getWriter();
        out.print("注销成功");

    }
}
