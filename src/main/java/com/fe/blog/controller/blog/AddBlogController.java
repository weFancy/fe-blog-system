package com.fe.blog.controller.blog;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.Blog;
import com.fe.blog.bean.Field;
import com.fe.blog.bean.ResponseData;
import com.fe.blog.bean.Tag;
import com.fe.blog.service.BlogService;
import com.fe.blog.service.FieldService;
import com.fe.blog.service.Impl.BlogServiceImpl;
import com.fe.blog.service.Impl.FIeldServiceImpl;
import com.fe.blog.service.Impl.TagServiceImpl;
import com.fe.blog.service.TagService;
import com.fe.blog.utils.GetRequestJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Fancy.we
 */
@WebServlet(name = "AddBlogController", value = "/AddBlogController")
public class AddBlogController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        JSONObject json = GetRequestJson.getRequestJsonObject(request);

        String title = json.getString("title");
        String content = json.getString("content");
        String description = json.getString("description");
        String type = json.getString("type");
        int userId = json.getIntValue("userId");
        String author = json.getString("author");
        JSONArray tag = json.getJSONArray("tag");
        String field = json.getString("field");


        FieldService fs = new FIeldServiceImpl();
        Field field1 = fs.selectByField(field);

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setDescription(description);
        blog.setType(type);
        blog.setFieldId(field1.getFieldId());

        blog.setUserId(userId);

        blog.setAuthor(author);

        BlogService blogService = new BlogServiceImpl();
        boolean addBlog = blogService.addBlog(blog);

        TagService tagService = new TagServiceImpl();
        List<Tag> blogTag =null;
        boolean tagResult = false;
        ArrayList<String> tags = new ArrayList<>();
        for (Object o : tag) {
            tags.add((String) o);
        }
        //循环往tag_relation表插入数据
        blogTag=tagService.selectByTag(tags);
        for(Tag item:blogTag){
            tagResult = blogService.addRelation(blog.getBlogId(), item.getTagId());
        }
        //查询博客的所有标签
        List<Tag> taglist = blogService.selectTagsByBlog(blog.getBlogId());
        //查询刚插入的博客
        Blog newBlog = blogService.selectInBlog(blog.getBlogId());
        HashMap<String, Object> map = new HashMap<String, Object>(2);

        map.put("blog",newBlog);
        map.put("tags",taglist);
        ResponseData responseData = new ResponseData();
        ObjectMapper mapper = new ObjectMapper();

        if (addBlog == true && tagResult == true) {
            responseData.setMsg("发布成功");
            responseData.setError("无");
            responseData.setData(map);
        } else if(tagResult!=true){
            responseData.setMsg("发布失败");
            responseData.setError("标签库不存在这样的标签");
            responseData.setData(null);
        } else{
            responseData.setMsg("发布失败");
            responseData.setError("原因未知！接收错误");
            responseData.setData(null);
        }
        String resultJson = mapper.writeValueAsString(responseData);
        PrintWriter writer = response.getWriter();
        writer.print(resultJson);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
