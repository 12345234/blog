package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class WebSocketController {

    //声明原子变量类，确保服务端和客户端之间操作的原子性和可见性

    private AtomicInteger atomicInteger=new AtomicInteger();

    @RequestMapping("/chat")
    public String chat(Model model) {
        model.addAttribute("username","user" + atomicInteger.getAndIncrement());
        return "chat";
    }
}
