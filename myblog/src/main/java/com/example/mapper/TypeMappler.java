package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TypeMappler extends BaseMapper<Type> {

    //新增保存分类

    @Insert("insert into t_type values(#{id},#{name})")
    int saveType(Type type);

    //根据id查询分类

    @Select("select * from t_type where id=#{id}")
    Type getType(Long id);

    //查询所有分类

    @Select("select * from t_type")
    List<Type> getAllType();

    //根据分类名称查询分类

    @Select("select * from t_type where name=#{name}")
    Type getTypeByName(String name);

    //编辑修改分类

    @Update("update t_type set name=#{name} where id=#{id}")
    int updateType(Long id,Type type);

    //删除分类

    @Delete("delete from t_type where id=#{id}")
    void deleteType(Long id);

    //查询所有分类

    @Select("select * from t_type")
    List<Type> getAllTypeAndBlog();

/*

    @Select("  select t.id tid, t.name, b.id bid, b.title,b.type_id from t_type t,t_blog b where t.id = b.type_id")
*/

    @Select("  select DISTINCT t.name from blog.t_type t,blog.t_blog b where t.id = b.type_id ")
    List<Type> getAllTypeIndex();

}
