package com.fe.blog.mapper.comment;

import com.fe.blog.bean.Comment;
import org.apache.ibatis.annotations.Select;


/**
 * @author Fancy.we
 */
public interface SelectOneComment {

    @Select("select * from comment where comment_id = #{id}")
    Comment select(int id);
}
