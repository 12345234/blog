package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 搜索博客管理列表
 * @Date: Created in 14:16 2020/6/8
 * @Author: ONESTAR
 * @QQ群: 530311074
 * @URL: https://onestar.newstar.net.cn/
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBlog {

    private String title;
    private Long typeId;

    //推荐符号从前端传过来是String类型

    private String recommend;
    private Integer recommend2;


}