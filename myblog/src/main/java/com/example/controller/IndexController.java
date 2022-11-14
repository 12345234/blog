package com.example.controller;

import com.example.entity.Comment;
import com.example.entity.Tag;
import com.example.entity.Type;
import com.example.service.impl.BlogServiceImpl;
import com.example.service.impl.CommentServiceImpl;
import com.example.service.impl.TagServiceImpl;
import com.example.service.impl.TypeServiceImpl;
import com.example.vo.DetailedBlog;
import com.example.vo.FirstPageBlog;
import com.example.vo.RecommendBlog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author admin
 */
@Controller
public class IndexController {

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private CommentServiceImpl commentService;

    //分页查询博客列表
    
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        /*查询首页最新博客列表信息*/
        List<FirstPageBlog> firstPageBlog = blogService.getFirstPageBlog();
        /*查询推荐文章*/
        List<RecommendBlog> recommendBlogs = blogService.getAllRecommendBlog();
        List<Type> types = typeService.getAllTypeIndex();
      /*  List<Type> types = typeService.getAllType();*/
/*        List<Tag> allType = tagService.getAllType();*/
        List<Tag> tags = tagService.getAllTagIndex();

        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(firstPageBlog);
        System.out.println("pageInfo"+pageInfo);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("recommendedBlogs", recommendBlogs);
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        return "index";
    }

    //搜索博客

    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 1000);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @RequestMapping("/blog/{id}")
    public String blog(Model model,@PathVariable Long id){
        List<Comment> comments = commentService.listCommentByBlogId2(id);
        model.addAttribute("comments", comments);
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }


    // 截取最后三个最新的数据

    @GetMapping("/footer/newBlog")
    public String newBlogs(Model model){
        List<RecommendBlog> allRecommendBlog = blogService.getAllRecommendBlog();
        List<RecommendBlog> list;
        if (allRecommendBlog.size()<4){
            list=allRecommendBlog;
        }else {
            list =allRecommendBlog.subList(allRecommendBlog.size()-3,allRecommendBlog.size());
        }

        model.addAttribute("newBlogs",list);
        return "_fragments ";
    }
}
