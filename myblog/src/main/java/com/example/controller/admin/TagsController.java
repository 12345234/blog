package com.example.controller.admin;

import com.example.entity.Blog;
import com.example.entity.Tag;
import com.example.entity.Type;
import com.example.service.impl.TagServiceImpl;
import com.example.service.impl.TypeServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author admin
 */
@Controller
@RequestMapping("/admin")
public class TagsController {
    @Autowired
    private TagServiceImpl tagService;

    @GetMapping("/tags")
    public String getList(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<Tag> allType = tagService.getAllType();
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(allType);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tags",new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag type, RedirectAttributes attributes){
        Type save = tagService.getTagByName(type.getName());
        if (save != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/tags/input";
        }
        int i = tagService.saveType(type);
        if (i == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    //    跳转修改分类页面

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        Tag byId = tagService.getType(id);
        model.addAttribute("tags",byId);
        return "admin/tags-input";
    }

    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag type,@PathVariable Long id,RedirectAttributes attributes){
        Type save = tagService.getTagByName(type.getName());
        if (save != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/tags/input";
        }
        int i = tagService.updateType(id, type);
        if (i == 0) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

}
