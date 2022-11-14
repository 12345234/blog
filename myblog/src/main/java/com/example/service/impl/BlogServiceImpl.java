package com.example.service.impl;

import com.example.File.NotFoundException;
import com.example.entity.Blog;
import com.example.mapper.BlogMapper;
import com.example.service.BlogService;
import com.example.util.MarkdownUtils;
import com.example.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;


    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogMapper.saveBlog(blog);
    }

    @Override
    public List<BlogQuery> getAllBlogQuery() {
        return blogMapper.getAllBlog();
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }

    @Override
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
       return   blogMapper.updateBlog(showBlog);

    }

    @Override
    public ShowBlog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public List<BlogQuery> searchByTitleAndType(SearchBlog searchBlog) {
        return blogMapper.searchByTitleAndType(searchBlog);
    }

    @Override
    public List<FirstPageBlog> getFirstPageBlog() {
        List<FirstPageBlog> firstPageBlog = blogMapper.getFirstPageBlog();
        return firstPageBlog;
    }

    @Override
    public List<RecommendBlog> getAllRecommendBlog() {
        return blogMapper.getAllRecommendBlog();
    }

    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        List<FirstPageBlog> searchBlog = blogMapper.getSearchBlog(query);
        return searchBlog;
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogMapper.getDetailedBlog(id);
        if (detailedBlog==null){
            throw new NotFoundException("找不到该博客");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return detailedBlog;
    }

    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogMapper.getByTypeId(typeId);
    }

    @Override
    public List<FirstPageBlog> getByTagId(Long tagId) {
        return blogMapper.getByTagId(tagId);
    }

    @Override
    public void transformRecommend(SearchBlog searchBlog) {
        if (!"".equals(searchBlog.getRecommend()) && null != searchBlog.getRecommend()) {
            searchBlog.setRecommend2(1);
        }
    }

    @Override
    public List<String> findGroupYear() {
        return blogMapper.findGroupYear();
    }

    // 根据年份显示

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.findGroupYear();
        Map<String,List<Blog>> map =new HashMap<>();
        for (String year: years) {
                map.put(year,blogMapper.findByYear(year));
        }
        return map;
    }

    // 找出数据总数

    @Override
    public Long countBlog() {
        return blogMapper.countBlog();
    }

    @Override
    public DetailedBlog getBlogByID(Long id) {
        return blogMapper.getBlogByID(id);
    }

    @Override
    public DetailedBlog getDetailedBlogComment(Long id) {
        return blogMapper.getDetailedBlogComment(id);
    }

    @Override
    public DetailedBlog getCommentBlodId(Long id) {
        return blogMapper.getCommentBlodId(id);
    }

}
