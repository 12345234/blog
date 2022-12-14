package com.example.service.impl;

import com.example.entity.Comment;
import com.example.mapper.BlogMapper;
import com.example.mapper.CommentMapper;
import com.example.service.CommentService;
import com.example.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogMapper blogMapper;


    @Override
    public List<Comment> listCommentByBlogId() {
        //没有父节点的默认为-1
        List<Comment> comments = commentMapper.findByBlogIdParentIdNull();
        return eachComment(comments);
    }

    @Override
    //接收回复的表单

    public int saveComment(Comment comment) {
        System.out.println("comment:" + comment);
        //判断有没有在别人的评论上进行评论还是一个新的评论
        Long parentCommentId = comment.getParentCommentId();
        //没有父级评论默认是-1
        if (parentCommentId != -1) {
            //有父级评论
            comment.setParentComment(commentMapper.findByParentCommentId(comment.getParentCommentId()));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        comment.setBlog(blogMapper.getDetailedBlog(comment.getBlogId()));
        return commentMapper.saveComment(comment);
    }


    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        //顶节点添加到临时存放集合

        tempReplys.add(comment);
        if (comment.getReplyComments().size()>0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }
    //删除评论

    @Override
    public void deleteComment(Comment comment,Long id) {
        commentMapper.deleteComment(id);
    }

    @Override
    public List<Comment> listCommentByBlogId2(Long blogId) {
        //没有父节点的默认为-1
        List<Comment> comments = commentMapper.findByBlogIdParentIdNull2( blogId,Long.parseLong("-1"));
        return eachComment(comments);
    }

    @Override
    public List<Comment> comments() {
        List<Comment> comments = commentMapper.comments();
        return comments;
    }

    @Override
    public int insert(CommentVo commentVo) {
        int insert = commentMapper.insert(commentVo);
        return insert;
    }

}
