package com.fe.blog.controller.tag;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.Blog;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.service.Impl.TagServiceImpl;
import com.fe.blog.service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Fancy.we
 */
@WebServlet("/TagFindblog")
public class TagFindBlogController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String tagname = req.getParameter("tagname");
        TagService tagService = new TagServiceImpl();

        List<Blog> selectblogbytag = tagService.selectblogbytag(tagname);
        List<Map> mapList =new ArrayList<>();
        for (Blog blog : selectblogbytag) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("blogid",blog.getBlogId());
            map.put("title",blog.getTitle());
            mapList.add(map);
        }

       ObjectMapper objectMapper = new ObjectMapper();
        ResponseData responseData = new ResponseData();

        if(mapList != null){
            responseData = new ResponseData("查找成功","成功",mapList);
        }
        else{
            responseData = new ResponseData("查找失败","失败",null);
        }

        String json = objectMapper.writeValueAsString(mapList);
       resp.getWriter().write(json);
    }
}
