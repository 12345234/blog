package com.example;

import com.example.entity.Comment;
import com.example.entity.Type;
import com.example.entity.User;
import com.example.service.impl.BlogServiceImpl;
import com.example.service.impl.CommentServiceImpl;
import com.example.service.impl.TypeServiceImpl;
import com.example.service.impl.UserServiceImpl;
import com.example.vo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MyblogApplicationTests {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CommentServiceImpl commentService;
    @Test
    void contextLoads() {


        List<User> list = userService.list();
        System.out.println(list);

        User admin = userService.checkUser("admin", "123");
        if (admin==null){
            System.out.println("不存在");
        }else {
            System.out.println("存在");
        }
    }


    @Autowired
    private TypeServiceImpl typeService;

    @Test
    public void  test1(){
        Type type =new Type();
        type.setName("错误日志");
        type.setId(null);
        type.setBlogs(null);
        boolean save = typeService.save(type);
        System.out.println(save);
    }

    @Autowired
    private BlogServiceImpl blogService;

    @Test
    public void test2(){
        List<BlogQuery> allBlogQuery = blogService.getAllBlogQuery();

        allBlogQuery=allBlogQuery.stream()
                .map((e)->{
                    Type type = typeService.getType(e.getTypeId());
                    e.setType(type);
                    return e;
                }).collect(Collectors.toList());

        allBlogQuery.forEach(System.out::println);


/*        List<Type> allType = typeService.getAllType();
        System.out.println(allType);*/
    }

    @Test
    public void test3(){
  /*      ShowBlog blogById = blogService.getBlogById(1577879724075L);
        System.out.println(blogById);
    */
      /*  ShowBlog showBlog =new ShowBlog();
        showBlog.setUpdateTime(new Date());
        showBlog.setAppreciation(true);
        showBlog.setTitle("hello");
        int i = blogService.updateBlog(showBlog);
        System.out.println(i);*/

        SearchBlog searchBlog =new SearchBlog();
        searchBlog.setTitle("1212");
        searchBlog.setTypeId(3L);
        List<BlogQuery> blogQueries = blogService.searchByTitleAndType(searchBlog);
        System.out.println(blogQueries);
    }

    @Test
    public void test4(){
        List<RecommendBlog> allRecommendBlog = blogService.getAllRecommendBlog();
        List<RecommendBlog> list;
        if (allRecommendBlog.size()<4){
            list=allRecommendBlog;
        }else {
            list =allRecommendBlog.subList(allRecommendBlog.size()-3,allRecommendBlog.size());
        }
        System.out.println(list);
    }

    @Test
    public void test5(){
        List<Comment> comments = commentService.listCommentByBlogId();
        System.out.println(comments);
    }
}
