package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
