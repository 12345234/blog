package com.example.mapper;

import com.example.entity.Comment;
import com.example.vo.CommentVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {


    //根据创建时间倒序来排

    @Select("  select c.id cid,c.nickname,c.email,c.content,c.avatar,c.create_time,c.parent_comment_id\n" +
            "    from blog.t_comment c\n" +
            "    order by c.create_time desc")
    List<Comment> findByBlogIdParentIdNull();

    //查询父级对象

    @Select(" select c.id cid,c.nickname,c.email,c.content,c.avatar,c.create_time,\n" +
            "    c.parent_comment_id from blog.t_comment c where c.parent_comment_id = #{parentCommentId}")
    Comment findByParentCommentId(Long parentCommentId);


    //添加一个评论

    @Insert("  insert into blog.t_comment (nickname,email,content,avatar,create_time)\n" +
            "    values (#{nickname},#{email},#{content},#{avatar},#{createTime}")
    int saveComment(Comment comment);

    //删除评论

    @Delete("  delete from t_comment where id = #{id}")
    void deleteComment(Long id);


    @Select(" select c.id cid,c.nickname,c.email,c.content,c.avatar,c.create_time,c.blog_id,c.parent_comment_id\n" +
            "    from blog.t_comment c\n" +
            "    where c.blog_id = #{blogId} and c.parent_comment_id = #{blogParentId}\n" +
            "    order by c.create_time desc")
    List<Comment> findByBlogIdParentIdNull2(@Param("blogId")Long blogId,@Param("blogParentId") Long blogParentId);



    @Select("SELECT id,nickname,email,content,avatar,create_time FROM t_comment")
    List<Comment> comments();

    @Insert("insert into blog.t_comment (nickname,email,content,avatar,create_time) values (#{nickname},#{email},#{content},#{avatar},#{createTime})")
    int insert(CommentVo commentVo);
}
