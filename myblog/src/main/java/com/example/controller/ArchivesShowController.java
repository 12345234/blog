package com.example.controller;

import com.example.entity.Blog;
import com.example.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 */
@Controller
public class ArchivesShowController {

    @Autowired
    private BlogServiceImpl blogService;

    @GetMapping("/archives")
    public String archivers(Model model){
        Map<String, List<Blog>> map = blogService.archiveBlog();
        model.addAttribute("archiveMap",map);
        Long aLong = blogService.countBlog();
        model.addAttribute("blogCount",aLong);
        return "archives";
    }
}
