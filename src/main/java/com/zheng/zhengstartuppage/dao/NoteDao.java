package com.zheng.zhengstartuppage.dao;

import com.zheng.zhengstartuppage.entity.NoteEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:26
 */

@Mapper
public interface NoteDao {
    @Insert("insert into " +
            "note(user_id, content, title, ctime, mtime) " +
            "value(#{userId}, #{content}, #{title}, #{ctime}, #{mtime})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    long insertNote(NoteEntity noteEntity);

    @Delete("delete from note where id=#{id}")
    void deleteNote(long id);

    @Update("<script>" +
            "update note set " +
            "<if test = 'content != null'> content = #{content},</if>" +
            "<if test = 'title != null'> title = #{title},</if>" +
            "mtime = #{mtime} " +
            "where id = #{id}" +
            "</script>")
    void updateNote(NoteEntity noteEntity);

    @Select("select id, user_id, title, ctime, mtime from note where user_id=#{userId}")
    @Results(id = "note", value = {
            @Result(column = "user_id", property = "userId"),
    })
    List<NoteEntity> getNotesByUserId(long userId);

    @Select("select * from note where id=#{id}")
    @ResultMap("note")
    NoteEntity getNoteById(long id);
}
