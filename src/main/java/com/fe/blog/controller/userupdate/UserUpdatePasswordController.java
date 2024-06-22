package com.fe.blog.controller.userupdate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.bean.User;
import com.fe.blog.service.Impl.UserUpdateServiceImpl;
import com.fe.blog.service.UserUpdateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author Fancy.we
 */
@WebServlet(name = "UserUpdatePasswordServlet",urlPatterns = "/updatePassword")
public class UserUpdatePasswordController extends HttpServlet {

    private UserUpdateService userService=new UserUpdateServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user=(User)session.getAttribute("user");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        User user=(User)session.getAttribute("user");
        String username = user.getAccount();
        
        //previousPassword是当前密码，先验证是否输入正确
        String previousPassword = request.getParameter("previousPassword");
        ResponseData responseData = new ResponseData();
        //密码正确时
        if (userService.judgePassword(username,previousPassword)){
            //modifiedPassword是后面修改的密码
            String modifiedPassword = request.getParameter("modifiedPassword");
            userService.updatePassword(username,modifiedPassword);
            responseData.setMsg("修改密码成功");
        }else {//密码错误时
            responseData.setMsg("输入的密码错误，请重新输入");
            responseData.setError("输入的密码错误，请重新输入");
        }
        PrintWriter out = response.getWriter();
        System.out.println(responseData);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseData);
        out.print(json);
    }
}
