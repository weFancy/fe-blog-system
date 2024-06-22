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
public class Homepage {
    private int homepageid;
    private String title;
    private String description;
    private String welcome;
    private String banner;
    private String announcement;
}
