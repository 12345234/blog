package com.example.mapper;

import com.example.entity.Blog;
import com.example.vo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {

    //保存新增博客

    @Insert("  insert into t_blog (id,title, content, first_picture, flag,\n" +
            "    views, appreciation, share_statement, commentabled,published,\n" +
            "    recommend, create_time, update_time, type_id,tag_ids, user_id, description)\n" +
            "    values (#{id},#{title},#{content},#{firstPicture},#{flag},#{views},#{appreciation},\n" +
            "    #{shareStatement},#{commentabled},#{published},#{recommend},#{createTime},\n" +
            "    #{updateTime},#{typeId},#{tagIds},#{userId},#{description});")
    int saveBlog(Blog blog);

    //查询文章管理列表

    @Select("  select b.id,b.title,b.update_time,b.recommend,b.published,b.type_id,t.id,t.name\n" +
            "   from t_blog b left outer join\n" +
            "    t_type t on b.type_id = t.id order by b.update_time desc")
    List<BlogQuery> getAllBlog();


    @Delete(" delete from t_blog where id = #{id}")
    void deleteBlog(Long id);


    //编辑博客

    @Update("  update  t_blog\n" +
            "    set published = #{published},flag = #{flag} ,\n" +
            "    title = #{title}, content = #{content}, type_id = #{typeId},\n" +
            "    first_picture = #{firstPicture} , description = #{description} , recommend = #{recommend} ,\n" +
            "    share_statement = #{shareStatement}, appreciation = #{appreciation},\n" +
            "    commentabled = #{commentabled} ,update_time = #{updateTime}\n" +
            "    where id = #{id};")
    int updateBlog(ShowBlog showBlog);

    //查询编辑修改的文章

    @Select(" select * from t_blog where id = #{id};")
    ShowBlog getBlogById(Long id);


    // 搜索

    @Select(" select b.id,b.title,b.type_id,t.id,t.name from t_blog b ,t_type t where  b.type_id = t.id  and b.type_id = #{typeId} and b.title like #{title}")
    List<BlogQuery> searchByTitleAndType(SearchBlog searchBlog);


    //查询首页最新博客列表信息

    @Select(" select b.id,b.title,b.first_picture, b.views,b.update_time,b.description,\n" +
            "    t.name ,\n" +
            "    u.nickname, u.avatar\n" +
            "    from t_blog b, t_type t,t_user u \n" +
            "    where b.type_id = t.id and  u.id = b.user_id order by b.update_time desc")
    List<FirstPageBlog> getFirstPageBlog();

    //查询首页最新推荐信息

    @Select("select * from t_blog where t_blog.recommend = true order by t_blog.create_time desc limit 4;")
    List<RecommendBlog> getAllRecommendBlog();


    @Select("select b.id,b.title,b.first_picture, b.views,b.update_time,b.description,t.name , u.nickname, u.avatar\n" +
            "    from  t_blog b,  t_type t, t_user u\n" +
            "    where b.type_id = t.id and  u.id = b.user_id and (b.title like #{query} or b.content like  #{query})\n" +
            "    order by b.update_time desc")
    List<FirstPageBlog> getSearchBlog(String query);


    @Select("  select b.id bid,b.first_picture,b.flag,b.title,b.content,b.views,b.update_time,b.commentabled,b.share_statement,b.appreciation, u.nickname,u.avatar ,t.id tid ,t.name \n" +
            "\n" +
            "\t\tFROM t_blog b,t_user u, t_type t \n" +
            "\t\t\t\n" +
            "\t\t\twhere b.user_id = u.id and b.id= #{id} and t.id =b.type_id")
    DetailedBlog getDetailedBlog(Long id);

    @Select(" select b.id bid,b.first_picture,b.flag,b.title,b.content,b.views,b.update_time,b.commentabled,b.share_statement,b.appreciation, u.nickname,u.avatar  from blog.t_blog b,blog.t_user u,blog.t_tag t,blog.t_blog_tags tb where b.user_id = u.id and tb.blog_id = b.id and b.id =#{id}")
    DetailedBlog getDetailedBlogComment(Long id);

    @Select("select b.id,b.title,b.first_picture, b.views,b.update_time,b.description,\n" +
            "    t.name ,\n" +
            "    u.nickname, u.avatar\n" +
            "    from t_blog b,t_type t,t_user u\n" +
            "    where b.type_id = t.id and u.id = b.user_id and b.type_id = #{typeId} order by b.update_time desc")
    List<FirstPageBlog> getByTypeId(Long typeId);


    @Select(" select b.id,b.title,b.first_picture,b.views,b.update_time,b.description,\n" +
            "    t.name,\n" +
            "    u.nickname,u.avatar\n" +
            "    from t_blog b,t_type t,t_user u ,t_blog_tags tb,blog.t_tag t1\n" +
            "    where b.type_id = t.id and u.id = b.user_id and tb.blog_id = b.id and tb.tag_id = t1.id and t1.id = #{tagId}\n" +
            "    order by b.update_time desc")
    List<FirstPageBlog> getByTagId(Long tagId);

    /*归档*/

    /*查询年份*/

    @Select("SELECT  DATE_FORMAT(b.update_time,'%Y') as Year from t_blog b GROUP BY YEAR ORDER BY Year DESC")
    List<String> findGroupYear();


    /*根据年份归类*/

    @Select("SELECT b.id,b.title ,b.flag ,b.update_time,DATE_FORMAT(b.update_time,'%Y') as Year FROM t_blog b  where DATE_FORMAT(b.update_time,'%Y')=2022")
    List<Blog> findByYear(String year);

    @Select("SELECT COUNT(*) FROM t_blog")
    Long countBlog();



    @Select(" select id,title,content,first_picture,\n" +
            "    flag,description,appreciation,share_statement,commentabled,published,\n" +
            "    recommend, create_time ,update_time\n" +
            "     ,type_id from t_blog where id=#{id};")
    DetailedBlog getBlogByID(Long id);



    @Select("  select b.id bid,b.first_picture,b.flag,b.title,b.content,b.views,b.update_time,b.commentabled,b.share_statement,b.appreciation, u.nickname,u.avatar,\n" +
            "           t.id tid ,t.name\n" +
            "    from t_blog b,t_user u,t_tag t,t_blog_tags tb\n" +
            "    where b.user_id = u.id\n" +
            "      and tb.blog_id = b.id\n" +
            "      and tb.tag_id = t.id\n" +
            "      and  b.id = #{id}" )
    DetailedBlog getCommentBlodId(Long id);
}
