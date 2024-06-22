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
@WebServlet("/FieldFindBlog")
public class FieldFindBlogController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String fieldid = req.getParameter("fieldid");
        String blogtitle = req.getParameter("blogtitle");
        int Userid = Integer.parseInt(req.getParameter("userid"));
        int page = Integer.parseInt(req.getParameter("page"));
        int size = Integer.parseInt(req.getParameter("size"));

        List<Blog> blog = null;
        FieldService fieldService = new FIeldServiceImpl();
        if(fieldid.equals("all")){
            BlogService blogService = new BlogServiceImpl();
            blog = blogService.fuzzyQuery(Userid,blogtitle,page,size);
        }
        else {
            blog = fieldService.selectBlogbytitle(Integer.parseInt(fieldid),Userid,blogtitle,page,size);
        }

        for (Blog blog1 : blog) {blog1.setContent(null);}

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseData responseData = null;

        if(blog != null){
            responseData = new ResponseData("查找成功","成功",blog);
        }
        else{
            responseData = new ResponseData("查找失败","失败",null);
        }
        String json = objectMapper.writeValueAsString(responseData);
        resp.getWriter().write(json);

    }
}
