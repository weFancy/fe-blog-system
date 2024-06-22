package com.fe.blog.controller.userlogin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.bean.User;
import com.fe.blog.service.UserLogService;
import com.fe.blog.service.Impl.UserLogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;



/**
 * @author Fancy.we
 */
@WebServlet(name = "UserLoginController",urlPatterns = "/UserLoginController")
public class UserLoginController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        req.setCharacterEncoding("UTF-8");
        User user =null;

        HttpSession session = req.getSession();
        user=(User)session.getAttribute("user");
        ResponseData responseData = new ResponseData();
        if(user == null){
            responseData.setMsg("没有登陆");
            responseData.setError("无");
            responseData.setData(null);
        }else{
            responseData.setMsg("已经登陆");
            responseData.setError("无");
            responseData.setData(null);
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseData);
        PrintWriter writer = resp.getWriter();
        writer.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        req.setCharacterEncoding("UTF-8");

        String account = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(account + password);

        // 获取用户输入的验证码
        String vericode = req.getParameter("vericode");
        // 获取后端传递的验证码
        String generatedCode= (String) req.getSession().getAttribute("verityCode");

        System.out.println(vericode +""+ generatedCode);

        UserLogService userService = new UserLogServiceImpl();
        User user = userService.Login(account, password);

        ResponseData responseData = new ResponseData();
        ObjectMapper mapper = new ObjectMapper();

        if(!vericode.toLowerCase().equals(generatedCode.toLowerCase())){
            responseData.setMsg("验证码错误");
            responseData.setError("验证码错误");
            responseData.setData(null);
        }else {
            if(user != null){
                responseData.setMsg("您的用户名为:" + account + "您的密码为:" + password);
                responseData.setError("无");
                responseData.setData(user);
                req.getSession().setAttribute("user", user);
            }else{
                responseData.setMsg("用户名或密码错误");
                responseData.setError("用户名或密码错误");
                responseData.setData(null);
            }

        }
        String json = mapper.writeValueAsString(responseData);
        PrintWriter writer = resp.getWriter();
        writer.print(json);
    }
}
