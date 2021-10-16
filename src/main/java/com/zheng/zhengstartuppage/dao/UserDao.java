package com.zheng.zhengstartuppage.dao;

import com.zheng.zhengstartuppage.entity.user.UserDataEntity;
import com.zheng.zhengstartuppage.entity.user.UserEntity;
import org.apache.ibatis.annotations.*;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:25
 */

@Mapper
public interface UserDao {
    @Insert("insert into " +
            "user(account, password, ctime, mtime) " +
            "value(#{account}, #{password}, #{ctime}, #{mtime})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    long insertUser(UserEntity userEntity);

    @Insert("insert into " +
            "user_data(user_id, nickname, city_name, second_display, cat_display, last_login_time, ctime, mtime) " +
            "value(#{userId}, #{nickname}, #{cityName}, #{secondDisplay}, #{catDisplay}, #{lastLoginTime}, #{ctime}, #{mtime})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    long insertUserData(UserDataEntity userEntity);

    @Select("select * from user where id=#{id}")
    UserEntity getUser(long id);

    @Select("select * from user where account=#{account}")
    UserEntity getUserByAccount(String account);

    @Select("select * from user_data where user_id=#{userId}")
    @Results(id = "userData", value = {
            @Result(column = "user_id", property = "userId"),
            @Result(column = "search_engine", property = "searchEngine"),
            @Result(column = "city_name", property = "cityName"),
            @Result(column = "second_display", property = "secondDisplay"),
            @Result(column = "cat_display", property = "catDisplay"),
            @Result(column = "last_login_time", property = "lastLoginTime"),
    })
    UserDataEntity getUserDataByUser(long userId);

    @Update("<script>" +
            "update user set " +
            "<if test = 'password != null'> password = #{password},</if>" +
            "mtime = #{mtime} " +
            "where id = #{id}" +
            "</script>")
    void updateUser(UserEntity userEntity);

    @Update("<script>" +
            "update user_data set " +
            "<if test = 'nickname != \"\" and nickname != null'> nickname = #{nickname},</if>" +
            "<if test = 'searchEngine != \"\" and searchEngine != null'> search_engine = #{searchEngine},</if>" +
            "<if test = 'cityName != \"\" and cityName != null'> city_name = #{cityName},</if>" +
            "<if test = 'secondDisplay != 0'> second_display = #{secondDisplay},</if>" +
            "<if test = 'catDisplay != 0'> cat_display = #{catDisplay},</if>" +
            "<if test = 'lastLoginTime != 0'> last_login_time = #{lastLoginTime},</if>" +
            "mtime = #{mtime} " +
            "where user_id = #{userId}" +
            "</script>")
    void updateUserData(UserDataEntity userDataEntity);

    @Update("<script>" +
            "update user_data set " +
            "<if test = 'nickname != \"\" and nickname != null'> nickname = #{nickname},</if>" +
            "<if test = 'searchEngine != \"\" and searchEngine != null'> search_engine = #{searchEngine},</if>" +
            "<if test = 'cityName != \"\" and cityName != null'> city_name = #{cityName},</if>" +
            "<if test = 'secondDisplay != 0'> second_display = #{secondDisplay},</if>" +
            "<if test = 'catDisplay != 0'> cat_display = #{catDisplay},</if>" +
            "<if test = 'lastLoginTime != 0'> last_login_time = #{lastLoginTime},</if>" +
            "mtime = #{mtime} " +
            "where user_id = #{userId}" +
            "</script>")
    void updateUserDataByUserId(UserDataEntity userDataEntity);

    @Delete("delete from user where id=${id}")
    void deleteUser(long id);

    @Delete("delete from user_data where user_id=#{userId}")
    void deleteUserData(long userId);
}
