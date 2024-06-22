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
@WebServlet(name = "ModifyBlogController", value = "/ModifyBlogController")
public class ModifyBlogController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        JSONObject json = GetRequestJson.getRequestJsonObject(request);


        String title = json.getString("title");
        String content = json.getString("content");
        String description = json.getString("description");
        String type = json.getString("type");
        int blogId = json.getIntValue("blogId");
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
        blog.setBlogId(blogId);
        blog.setUserId(userId);
        blog.setAuthor(author);
        BlogService blogService = new BlogServiceImpl();
        boolean modifyBlog = blogService.modifyBlog(blog);
        //删除博客对应的所有标签
        blogService.deleteBlogTag(blogId);

        TagService tagService = new TagServiceImpl();
        List<Tag> blogTag =null;
        boolean tagResult = false;
        ArrayList<String> tags = new ArrayList<>();
        for (Object o : tag) {
            tags.add((String) o);
        }
        //给博客新增标签关系
        blogTag=tagService.selectByTag(tags);
        for(Tag item:blogTag){
            tagResult = blogService.addRelation(blog.getBlogId(), item.getTagId());
        }
        //查询刚更新的博客
        Blog newBlog = blogService.selectInBlog(blogId);
        List<Tag> taglist = blogService.selectTagsByBlog(blog.getBlogId());

        HashMap<String, Object> map = new HashMap<String, Object>(2);

        map.put("blog",newBlog);
        map.put("tags",taglist);
        map.put("field",field);
        ResponseData responseData = new ResponseData();
        ObjectMapper mapper = new ObjectMapper();

        if(modifyBlog == true && tagResult == true){
            responseData.setMsg("操作成功");
            responseData.setError("无");
            responseData.setData(map);
        }else{
            responseData.setMsg("操作失败");
            responseData.setError("原因未知，请稍后重试");
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
