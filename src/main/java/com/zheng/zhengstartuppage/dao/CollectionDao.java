package com.zheng.zhengstartuppage.dao;

import com.zheng.zhengstartuppage.entity.CollectionEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:26
 */

@Mapper
public interface CollectionDao {
    @Insert("insert into " +
            "note(user_id, url, name, color, ctime, mtime) " +
            "value(#{userId}, #{url}, #{name}, #{color}, #{ctime}, #{mtime})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    long insertNote(CollectionEntity collectionEntity);

    @Delete("delete from collection where id=#{id}")
    void deleteNote(long id);

    @Select("select * from collection where user_id=#{userId}")
    @Results(id = "todo", value = {
            @Result(column = "user_id", property = "userId"),
    })
    List<CollectionEntity> getNotesById(long userId);
}
