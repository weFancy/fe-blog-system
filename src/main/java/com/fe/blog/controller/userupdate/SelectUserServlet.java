package com.fe.blog.controller.userupdate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.User;
import com.fe.blog.service.Impl.UserUpdateServiceImpl;
import com.fe.blog.service.UserUpdateService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author Fancy.we
 */
@WebServlet(name = "SelectUserServlet", value = "/SelectUserServlet")
public class SelectUserServlet extends HttpServlet {
    private UserUpdateService userService=new UserUpdateServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取登录的user对象，如果没有用会话对象，返回null
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        HttpSession session = request.getSession(false);
        User user=(User)session.getAttribute("user");
        User info = userService.getInfo(user.getAccount());
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        out.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
