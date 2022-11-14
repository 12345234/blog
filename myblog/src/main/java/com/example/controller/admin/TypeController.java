package com.example.controller.admin;

import com.example.entity.Type;
import com.example.service.impl.TypeServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author admin
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeServiceImpl service;

    @GetMapping("/types")
    public String getList(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<Type> list = service.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<Type>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    //    返回新增分类页面

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }


    @PostMapping("/types")
    public String post(@Valid Type type,RedirectAttributes attributes){
        Type save = service.getTypeByName(type.getName());
        if (save != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int i = service.saveType(type);
        if (i == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

        //    跳转修改分类页面

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        Type byId = service.getType(id);
        model.addAttribute("type",byId);
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type,@PathVariable Long id,RedirectAttributes attributes){
        Type save = service.getTypeByName(type.getName());
        if (save != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int i = service.updateType(id, type);
        if (i == 0) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        service.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }


}
