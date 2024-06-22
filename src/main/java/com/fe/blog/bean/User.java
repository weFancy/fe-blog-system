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
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
public class User {
    private String account;
    private String password;
    private String email;
    private String nick;
    private String profile;
    private String avatar;
    private String sex;
    private Date birthday;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    private int deleted;
}
