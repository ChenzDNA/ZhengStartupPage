package com.zheng.zhengstartuppage.dao;

import com.zheng.zhengstartuppage.entity.TodoEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:25
 */

@Mapper
public interface TodoDao {
    @Insert("insert into " +
            "todo(user_id, content, finished, ctime, mtime) " +
            "value(#{userId}, #{content}, #{finished}, #{ctime}, #{mtime})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    long insertTodo(TodoEntity todoEntity);

    @Delete("delete from todo where id=#{id}")
    void deleteTodo(long id);

    @Update("<script>" +
            "update todo set " +
            "<if test = 'content != \"\"'> content = #{content},</if>" +
            "<if test = 'finished != 0'> finished = #{finished},</if>" +
            "mtime = #{mtime} " +
            "where id = #{id}" +
            "</script>")
    void updateTodo(TodoEntity todoEntity);

    @Select("select * from todo where user_id=#{userId}")
    @Results(id = "todo", value = {
            @Result(column = "user_id", property = "userId"),
    })
    List<TodoEntity> getTodosByUserId(long userId);
}
