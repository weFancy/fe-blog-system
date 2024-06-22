package com.fe.blog.mapper.comment;

import com.fe.blog.bean.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Fancy.we
 */
public interface CommentMapper {

    /**
     * 查询一条博客下的所有评论
     * @param blogId 博客id, page当前页数, size每页显示的记录数
     * @return
     */
    List<Comment> selectAllComment(@Param("blogId") int blogId,@Param("page") int page,@Param("size") int size);

    /**
     * 增加对博客的评论
     */
    int addComment( Comment comment);

    /**
     * 删除对博客的评论
     */
    int deleteComment(int id);

    /**
     * 修改对博客的评论
     */
    int updateComment(Comment comment);

    /**
     * 查询当前用户下的评论总数
     */
    int selectCommentCount(@Param("blogId") int blogId);
}
