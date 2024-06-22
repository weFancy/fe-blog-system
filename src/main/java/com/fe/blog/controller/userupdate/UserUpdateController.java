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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Fancy.we
 */
@WebServlet(name = "UserUpdateServlet",urlPatterns = "/userUpdateServlet")
public class UserUpdateController extends HttpServlet {
    private UserUpdateService userService=new UserUpdateServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet2");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取登录的user对象，如果没有用会话对象，返回null
        HttpSession session = request.getSession(false);
        User user=(User)session.getAttribute("user");
        ResponseData responseData = new ResponseData();
        if (user!=null) {
            //从userinfo表单里获取属性值
            String nick = request.getParameter("nick");
            String date1 = request.getParameter("date");
            //将字符串转化为sql下的date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Date birthday = new java.sql.Date(date.getTime());
            String sex = request.getParameter("sex");
            if("boy".equalsIgnoreCase(sex)){
                sex="男";
            }else{
                sex="女";
            }
            String profile = request.getParameter("profile");
            user.setNick(nick);
            user.setBirthday(birthday);
            user.setSex(sex);
            user.setProfile(profile);
            User updateUser = userService.updateInfo(user);
            //
            if (updateUser==null){
                responseData.setError("抱歉,修改失败");
            }else{
                responseData.setMsg("修改成功");
            }
        }else{
            responseData.setError("当前还未登陆，登录后再试");
        }
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseData);
        out.print(json);
    }

}
