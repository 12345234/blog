package com.example.service;

import com.example.entity.Blog;
import com.example.vo.*;

import java.util.List;
import java.util.Map;

public interface BlogService {

    //保存新增博客

    int saveBlog(Blog blog);

    //查询文章管理列表

    List<BlogQuery> getAllBlogQuery();


    void deleteBlog(Long id);


    //编辑博客

    int updateBlog(ShowBlog showBlog);

    //查询编辑修改的文章

    ShowBlog getBlogById(Long id);

    List<BlogQuery> searchByTitleAndType(SearchBlog searchBlog);


    List<FirstPageBlog> getFirstPageBlog();


    List<RecommendBlog> getAllRecommendBlog();

    List<FirstPageBlog> getSearchBlog(String query);

    DetailedBlog getDetailedBlog(Long id);

    List<FirstPageBlog> getByTypeId(Long typeId);

    List<FirstPageBlog> getByTagId(Long tagId);

    void transformRecommend(SearchBlog searchBlog);


    List<String> findGroupYear();

    /*归档*/

    Map<String,List<Blog>> archiveBlog();

    /*找到有多少文章*/
    Long countBlog();

    DetailedBlog getBlogByID(Long id);

    DetailedBlog getDetailedBlogComment(Long id);

    DetailedBlog getCommentBlodId(Long id);
}
