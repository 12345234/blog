package com.example.mapper;

import com.example.entity.Tag;
import com.example.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagsMapper {

    //新增保存分类

    @Insert("insert into t_tag values(#{id},#{name})")
    int saveType(Tag tag);

    //根据id查询分类

    @Select("select * from t_tag where id=#{id}")
    Tag getType(Long id);

    //查询所有分类

    @Select("select * from t_tag")
    List<Tag> getAllType();

    //根据分类名称查询分类

    @Select("select * from t_tag where name=#{name}")
    Type getTagByName(String name);

    //编辑修改分类

    @Update("update t_tag set name=#{name} where id=#{id}")
    int updateType(Long id,Tag tag);

    //删除分类

    @Delete("delete from t_tag where id=#{id}")
    void deleteType(Long id);

    //查询所有分类

    @Select("select * from t_tag")
    List<Tag> getAllTagAndBlog();

/*    @Select("    select t.id tid,t.name,b.id bid,b.title from t_tag t,t_blog b, t_blog_tags bt\n" +
            "    where t.id = bt.tag_id and b.id = bt.blog_id")*/

    @Select(" select DISTINCT t.name from blog.t_tag t,blog.t_blog b, blog.t_blog_tags bt\n" +
            "    where t.id = bt.tag_id and b.id = bt.blog_id")
    List<Tag> getAllTagIndex();
}
