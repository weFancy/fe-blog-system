package com.fe.blog.mapper.blog;

import org.apache.ibatis.annotations.Select;

/**
 * @author Fancy.we
 */
public interface SelectBlogCount {
    @Select("select count(*) from blog where deleted = 0")
    int selectCount();
}
