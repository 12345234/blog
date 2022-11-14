package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 分类实体类
 * @Date: Created in 17:26 2020/5/31
 * @Author: ONESTAR
 * @QQ群: 530311074
 * @URL: https://onestar.newstar.net.cn/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {

    private Long id;

    @NotBlank(message = "分类名称不能为空")
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    private List<Comment> comments;

}