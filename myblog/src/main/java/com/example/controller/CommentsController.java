package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.entity.Comment;
import com.example.entity.User;
import com.example.service.impl.BlogServiceImpl;
import com.example.service.impl.CommentServiceImpl;
import com.example.vo.CommentVo;
import com.example.vo.DetailedBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class CommentsController {
    @Autowired
    private CommentServiceImpl commentService;


    // 设置默认头像

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments")
    public String comments(Model model){
        // 显示所有聊天
/*        List<Comment> comments = commentService.listCommentByBlogId();*/
        List<Comment> comments = commentService.comments();
        model.addAttribute("comments",comments);
        return "comment";
    }

    // 提交信息

    @RequestMapping("/comments")
    public String post(CommentVo comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user!=null){
            // 获取头像
            comment.setAvatar(user.getAvatar());
/*            comment.setAdminComment(true);*/
        }else {
            // 没有头像的，设置为默认头像
            comment.setAvatar(avatar);
        }

       /* commentService.saveComment(comment);*/
            comment.setCreateTime(new Date());
        commentService.insert(comment);
        return "redirect:/comments";
    }


    //    删除评论

    @GetMapping("/comment/{id}/delete")
    public String delete( @PathVariable Long id, Comment comment, RedirectAttributes attributes, Model model){
        commentService.deleteComment(comment,id);
        List<Comment> comments = commentService.listCommentByBlogId();
        model.addAttribute("comments", comments);
        return "comment";
    }

}