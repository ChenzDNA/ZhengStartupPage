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
            "user_data(user_id, nickname, search_engine, city_name, second_display, cat_display, last_login_time, ctime, mtime) " +
            "value(#{userId}, #{nickname}, #{searchEngine}, #{cityName}, #{secondDisplay}, #{catDisplay}, #{lastLoginTime}, #{ctime}, #{mtime})")
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
            "<if test = 'nickname != null'> nickname = #{nickname},</if>" +
            "<if test = 'search_engine != null'> search_engine = #{search_engine},</if>" +
            "<if test = 'city_name != null'> city_name = #{city_name},</if>" +
            "<if test = 'second_display != 0'> second_display = #{second_display},</if>" +
            "<if test = 'cat_display != 0'> cat_display = #{cat_display},</if>" +
            "<if test = 'last_login_time != 0'> last_login_time = #{last_login_time},</if>" +
            "mtime = #{mtime} " +
            "where id = #{id}" +
            "</script>")
    void updateUserData(UserDataEntity userDataEntity);
}
