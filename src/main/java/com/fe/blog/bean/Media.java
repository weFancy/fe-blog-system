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
public class Media {
    private int mediaId;
    private String image;
    private String music;
}
