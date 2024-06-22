package com.fe.blog.mapper.field;

import org.apache.ibatis.annotations.Select;

/**
 * @author Fancy.we
 */
public interface SelectFieldName {
    @Select("select name from field where field_id = #{fieldId}")
    String selectFieldName(int fieldId);
}
