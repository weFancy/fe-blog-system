package com.fe.blog.mapper.field;

import com.fe.blog.bean.Blog;
import com.fe.blog.bean.Field;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Fancy.we
 */
public interface FieldMapper {

    /**
     * 添加标签
     */
    public Integer addFieldTag(String  field);

    /**
     * 删除标签
     */
    public Integer deleteFieldTag(Integer Fieldtag);

    /**
     * 查询所有的标签
     */
    public List<Field> findAllFieldTag();

    /**
     * 根据标签名查询标签id
     * @param Fieldtag 要查询的标签名
     * @return 标签id
     */
    public Integer selectFieldTag(String Fieldtag);

    /**
     * 根据标签名查询标签id
     * @param field 要查询的标签名
     * @return Field
     */
    Field selectByField(String field);

    /**
     * 根据field查询所有blog信息
     */
    public List<Blog> selectBlogbyField(@Param("Fieldid") int Fieldid,@Param("Userid") int Userid, @Param("page") int page, @Param("size") int size);

    /**
     * 根据fieldid和blogtitle查询blog
     */
    public List<Blog> selectBlogbytitle(int Fieldid,int Userid,String blogtitle,@Param("page") int page, @Param("size") int size);
}
