package com.fe.blog.service;

import com.fe.blog.bean.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Fancy.we
 */
public interface CommentService {


    /**
     * 增加对博客的评论
     */
    Comment add(@Param("comment") Comment comment);

    /**
     * 删除对博客的评论
     */
    boolean deleted(@Param("id")int id);

    /**
     * 修改对博客的评论
     */
    Comment modify(@Param("comment") Comment comment);
    /**
     * 查询一条博客的所有评论
     */
    List<Comment> selectAll(@Param("blogId") int blogId,@Param("page") int page,@Param("size") int size);

    /**
     * 查询当前用户下的评论总数
     */

    int selectCommentCount(@Param("blogId") int blogId);
}
