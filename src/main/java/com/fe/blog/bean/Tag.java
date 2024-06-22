package com.fe.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;


/**
 * @author Fancy.we
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
public class Tag {
    private int tagId;
    private String name;
}
