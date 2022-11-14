package com.example.vo;

import com.example.entity.Comment;
import com.example.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 博客详情实体类
 * @Date: Created in 10:10 2020/6/19
 * @Author: ONESTAR
 * @QQ群: 530311074
 * @URL: https://onestar.newstar.net.cn/
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedBlog {

    //博客信息
    private Long id;
    private String firstPicture;
    private String flag;
    private String title;
    private String content;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private boolean commentabled;
    private boolean shareStatement;
    private boolean appreciation;
    private String nickname;
    private String avatar;
    private List<Tag> tags = new ArrayList<>();

    private List<Comment> comments =new ArrayList<>();
    //分类名称
    private String typeName;


}