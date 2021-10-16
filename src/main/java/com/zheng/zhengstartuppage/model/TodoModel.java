package com.zheng.zhengstartuppage.model;

import com.zheng.zhengstartuppage.dao.TodoDao;
import com.zheng.zhengstartuppage.entity.TodoEntity;
import com.zheng.zhengstartuppage.util.SessionUtil;
import com.zheng.zhengstartuppage.util.returns.ReturnsData;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:29
 */

@Repository
public class TodoModel {
    @Resource
    private TodoDao todoDao;

    public long insert(TodoEntity todoEntity) {
        todoEntity.setCtime(new Date().getTime());
        todoEntity.setUserId(SessionUtil.getUser().getId());
        return todoDao.insertTodo(todoEntity);
    }

    public void delete(long id) {
        todoDao.deleteTodo(id);
    }

    public void updateTodo(TodoEntity todoEntity) {
        todoDao.updateTodo(todoEntity);
    }

    public List<TodoEntity> getTodosByUserId(long userId) {
        return todoDao.getTodosByUserId(userId);
    }

    public long getTodoUserById(long id) {
        return todoDao.getTodoUserById(id);
    }
}
