package com.fe.blog.mapper.blog;

import com.fe.blog.bean.Tag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Fancy.we
 */
public interface SelectTagByBlog {
    /**
     * 根据博客id查询全部对应的标签
     */
    @Select("select name,tag_id from tag where tag_id = any(select tag_id from tag_relation " +
            "where blog_id = #{blogId} and deleted = 0) ")
    List<Tag> selectTagsByBlog(int blogId);
}
