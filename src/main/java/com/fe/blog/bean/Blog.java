package com.fe.blog.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;


/**
 * @author Fancy.we
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private int blogId;
    private String title;
    private String content;
    private String description;
    private String type;
    private String author;
    private int status;
    private int userId;
    private int fieldId;
    private int deleted;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
}
