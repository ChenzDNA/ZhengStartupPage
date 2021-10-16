package com.zheng.zhengstartuppage.service;

import com.zheng.zhengstartuppage.entity.TodoEntity;
import com.zheng.zhengstartuppage.model.TodoModel;
import com.zheng.zhengstartuppage.util.SessionUtil;
import com.zheng.zhengstartuppage.util.returns.AppResult;
import com.zheng.zhengstartuppage.util.returns.ReturnsData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : 陈征
 * @date : 2021-10-01 21:55
 */

@Service
public class TodoService {
    @Resource
    private TodoModel todoModel;

    public long insert(TodoEntity todoEntity) {
        return todoModel.insert(todoEntity);
    }

    public ReturnsData delete(long id) {
        if (todoModel.getTodoUserById(id) != SessionUtil.getUser().getId()) {
            return ReturnsData.returns("错误的用户！");
        }
        todoModel.delete(id);
        return ReturnsData.returns(id);
    }

    public ReturnsData update(TodoEntity todoEntity) {
        if (todoModel.getTodoUserById(todoEntity.getId()) != SessionUtil.getUser().getId()) {
            return ReturnsData.returns("错误的用户！");
        }
        todoModel.updateTodo(todoEntity);
        return ReturnsData.returns(todoEntity.getId());
    }

    public ReturnsData getTodos() {
        long userId = SessionUtil.getUser().getId();
        return ReturnsData.returns(todoModel.getTodosByUserId(userId).toArray());
    }

}
