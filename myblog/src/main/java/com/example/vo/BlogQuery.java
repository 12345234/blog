package com.example.vo;

import com.example.entity.Blog;
import com.example.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

/**
 * @Description: 查询博客列表
 * @Date: Created in 9:31 2020/6/3
 * @Author: ONESTAR
 * @QQ群: 530311074
 * @URL: https://onestar.newstar.net.cn/
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogQuery {

    private Long id;
    private String title;
    private Date createTime;
    private Date updateTime;
    private Boolean recommend;
    private Boolean published;
    private Long typeId;
    private Type type;
}