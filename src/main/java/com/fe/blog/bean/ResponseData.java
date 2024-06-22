package com.fe.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fancy.we
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
    private String msg;      //提示信息
    private String error;    //错误信息，没有就填无
    private Object data;     //数据，无就填null
}
