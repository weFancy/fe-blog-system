package com.fe.blog.controller.fieldcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.Blog;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.BlogService;
import com.fe.blog.service.FieldService;
import com.fe.blog.service.Impl.BlogServiceImpl;
import com.fe.blog.service.Impl.FIeldServiceImpl;

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
@WebServlet("/FieldfindAllblog")
public class FieldFindAllBlogController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String fieldname = req.getParameter("fieldname");
        int Userid = Integer.parseInt(req.getParameter("userid"));
        int page = Integer.parseInt(req.getParameter("page"));
        int size = Integer.parseInt(req.getParameter("size"));

        FieldService fieldService = new FIeldServiceImpl();
          List<Blog> blogs = null;
        if(fieldname.equals("all")){
            BlogService blogService = new BlogServiceImpl();
            blogs = blogService.selectByBlogLimit(Userid,page,size);
        }
        else{
            blogs = fieldService.selectBlogbyField(fieldService.findField(fieldname),Userid,page,size);
        }

        for (Blog blog : blogs) {blog.setContent(null);}

        ResponseData responseData = null;
        ObjectMapper objectMapper = new ObjectMapper();

        if(fieldService != null){
            responseData = new ResponseData("查找成功","成功",blogs);
        }
        else{
            responseData = new ResponseData("查找失败","失败",null);
        }
        String json = objectMapper.writeValueAsString(responseData);
        resp.getWriter().write(json);
    }
}
