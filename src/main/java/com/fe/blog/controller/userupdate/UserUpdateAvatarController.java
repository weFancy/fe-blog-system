package com.fe.blog.controller.userupdate;

import com.fe.blog.bean.ResponseData;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;


/**
 * @author Fancy.we
 */
@WebServlet(name = "UserUpdateAvatarController", value = "/UserUpdateAvatarController")

@MultipartConfig
public class UserUpdateAvatarController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端图片的路径应该是http://localhost:8080/fe_blog/images/avatar/avatar.jpg,否则刷新不了
        //获取前台的数据input输入流,name="avatar"
        Part part = request.getPart("avatar");
        InputStream is = part.getInputStream();
        //获得对应的头像地址，用前端传入的输入流将改头像覆盖
        String avatarPath =
                request.getSession().getServletContext().getRealPath("/") + "images\\avatar\\avatar.jpg";
        File file = new File(avatarPath);
        FileOutputStream os = new FileOutputStream(file);
        byte[] bb = new byte[1024];
        int len;
        while ((len = is.read(bb)) != -1) {
            os.write(bb, 0, len);
        }
        os.close();
        is.close();
        PrintWriter out = response.getWriter();
        
        String json = "{\n" +
                "  \"code\": 0\n" +
                "  ,\"msg\": \"\"\n" +
                "  ,\"data\": {\n" +
                "    \"src\": \"http://localhost:8080/fe_blog/images/avatar/avatar.jpg\"\n" +
                "  }\n" +
                "}";
        out.print(json);
        
    }
}

