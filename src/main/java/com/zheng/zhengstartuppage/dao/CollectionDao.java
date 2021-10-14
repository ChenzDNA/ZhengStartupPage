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
            "collection(user_id, url, name, color, ctime, mtime) " +
            "value(#{userId}, #{url}, #{name}, #{color}, #{ctime}, #{mtime})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    long insertCollection(CollectionEntity collectionEntity);

    @Delete("delete from collection where id=#{id}")
    void deleteCollection(long id);

    @Select("select * from collection where user_id=#{userId}")
    @Results(id = "collection", value = {
            @Result(column = "user_id", property = "userId"),
    })
    List<CollectionEntity> getCollectionsById(long userId);

    @Select("select user_id from collection where id=#{id}")
    long getCollectionUser(long id);
}
