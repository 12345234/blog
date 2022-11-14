package com.example.service;


import com.example.entity.Tag;
import com.example.entity.Type;

import java.util.List;

public interface TagService {
    //新增保存分类


    int saveType(Tag tag);

    //根据id查询分类


    Tag getType(Long id);

    //查询所有分类

    List<Tag> getAllType();

    //根据分类名称查询分类


    Type getTagByName(String name);

    //编辑修改分类

    int updateType(Long id,Tag tag);

    //删除分类


    void deleteType(Long id);

    //查询所有分类

    List<Tag> getAllTagAndBlog();

    List<Tag> getAllTagIndex();
}
