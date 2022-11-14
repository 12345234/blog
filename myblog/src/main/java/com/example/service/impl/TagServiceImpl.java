package com.example.service.impl;

import com.example.entity.Tag;
import com.example.entity.Type;
import com.example.mapper.TagsMapper;
import com.example.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagsMapper tagsMapper;


    @Override
    public int saveType(Tag tag) {
        return tagsMapper.saveType(tag);
    }

    @Override
    public Tag getType(Long id) {
        return tagsMapper.getType(id);
    }

    @Override
    public List<Tag> getAllType() {
        return tagsMapper.getAllType();
    }

    @Override
    public Type getTagByName(String name) {
        return tagsMapper.getTagByName(name);
    }

    @Override
    public int updateType(Long id, Tag tag) {
        return tagsMapper.updateType(id,tag);
    }

    @Override
    public void deleteType(Long id) {
        tagsMapper.deleteType(id);
    }

    @Override
    public List<Tag> getAllTagAndBlog() {
        return tagsMapper.getAllTagAndBlog();
    }

    @Override
    public List<Tag> getAllTagIndex() {
      return tagsMapper.getAllTagIndex();
    }
}
