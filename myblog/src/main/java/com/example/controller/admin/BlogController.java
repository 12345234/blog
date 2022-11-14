package com.example.controller.admin;

import com.example.entity.Blog;
import com.example.entity.Type;
import com.example.entity.User;
import com.example.service.impl.BlogServiceImpl;
import com.example.service.impl.TagServiceImpl;
import com.example.service.impl.TypeServiceImpl;
import com.example.vo.BlogQuery;
import com.example.vo.SearchBlog;
import com.example.vo.ShowBlog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private TypeServiceImpl typeService;
    @Autowired
    private TagServiceImpl tagService;

    //跳转博客新增页面

    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("blog", new Blog());
        model.addAttribute("tags",tagService.getAllTagAndBlog());
        return "admin/blogs-input";
    }

    //博客新增

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        //新增的时候需要传递blog对象，blog对象需要有user
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        int b = blogService.saveBlog(blog);
        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blogs";
    }

    //博客列表

    @RequestMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){

        //按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<BlogQuery> list = blogService.getAllBlogQuery();

        list =list.stream()
                .map((e)->{
                    Type type = typeService.getType(e.getTypeId());
                    e.setType(type);
                    return e;
                }).collect(Collectors.toList());

        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(list);
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }


    // 跳转修改文章

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        ShowBlog blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.getAllType();
        model.addAttribute("blog",blogById);
        model.addAttribute("types",allType);
        return "admin/blogs-input";
    }


    //编辑修改文章

    @PostMapping("/blogs/{id}")
    public String editPost(@Valid ShowBlog showBlog, RedirectAttributes attributes) {
        int b = blogService.updateBlog(showBlog);
        if(b == 0){
            attributes.addFlashAttribute("message", "修改失败");
        }else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/blogs";
    }


    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum, SearchBlog searchBlog,Model model){
        //将recommend转换一下
        blogService.transformRecommend(searchBlog);
        List<BlogQuery> blogBySearch = blogService.searchByTitleAndType(searchBlog);
        PageHelper.startPage(pageNum,3);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("message", "查询成功");
        return "admin/blogs";
    }
}
