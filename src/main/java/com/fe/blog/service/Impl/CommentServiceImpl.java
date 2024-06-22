package com.fe.blog.service.Impl;

import com.fe.blog.bean.Comment;
import com.fe.blog.mapper.comment.CommentMapper;
import com.fe.blog.mapper.comment.SelectOneComment;
import com.fe.blog.service.CommentService;
import com.fe.blog.utils.MybatisSession;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author Fancy.we
 */
public class CommentServiceImpl implements CommentService {
    SqlSession session;
    /**
     * 增加对博客的评论
     * @return true of false
     */
    @Override
    public Comment add(Comment comment) {

        session = MybatisSession.getSession();
        CommentMapper addMapper = session.getMapper(CommentMapper.class);
        int i = addMapper.addComment(comment);
        System.out.println(comment.getId());
        if(i!=0){

            SelectOneComment selectMapper = session.getMapper(SelectOneComment.class);
            Comment newComment = selectMapper.select(comment.getId());
            return newComment;
        }
        return null;
    }

    /**
     * 删除对博客的评论
     * @return true of false
     */
    @Override
    public boolean deleted(int id) {
        session = MybatisSession.getSession();
        CommentMapper mapper = session.getMapper(CommentMapper.class);
        int i = mapper.deleteComment(id);
        if(i!=0){
            return true;
        }
        return false;
    }

    /**
     * 修改对博客的评论
     * @return true of false
     */
    @Override
    public Comment modify(Comment comment) {
        session = MybatisSession.getSession();
        CommentMapper modifyMapper = session.getMapper(CommentMapper.class);
        int i = modifyMapper.updateComment(comment);
        SelectOneComment selectMapper = session.getMapper(SelectOneComment.class);
        Comment result = selectMapper.select(comment.getId());
        if(i!=0){
            return result;
        }
        return null;
    }

    /**
     * 查询一条博客的所有评论
     */
    @Override
    public List<Comment> selectAll(@Param("blogId") int blogId,@Param("page") int page,@Param("size") int size) {
        session = MybatisSession.getSession();
        CommentMapper mapper = session.getMapper(CommentMapper.class);
        List<Comment> comments = mapper.selectAllComment(blogId,page,size);

        return comments;
    }

    /**
     * 查询当前用户下的评论总数
     * @param blogId
     * @return
     */
    @Override
    public int selectCommentCount(@Param("blogId") int blogId) {
        session = MybatisSession.getSession();
        CommentMapper mapper = session.getMapper(CommentMapper.class);
        int count = mapper.selectCommentCount(blogId);
        return count;
    }
}
