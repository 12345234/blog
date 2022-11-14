package com.example.service;

import com.example.entity.Comment;
import com.example.vo.CommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId();

    int saveComment(Comment comment);
    //删除评论

    void deleteComment(Comment comment,Long id);

    public List<Comment> listCommentByBlogId2(Long blogId);

    List<Comment> comments();

    int insert(CommentVo commentVo);
}
