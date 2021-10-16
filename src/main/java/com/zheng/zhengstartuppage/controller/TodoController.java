package com.zheng.zhengstartuppage.controller;

import com.zheng.zhengstartuppage.entity.TodoEntity;
import com.zheng.zhengstartuppage.service.TodoService;
import com.zheng.zhengstartuppage.util.returns.AppResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author : 陈征
 * @date : 2021-10-14 15:01
 */

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Resource
    private TodoService todoService;

    @PostMapping("/insert")
    public AppResult insert(@Valid TodoEntity todoEntity, Errors errors) {
        if (errors.hasErrors()) {
            return AppResult.fail("含有特殊字符");
        }
        todoService.insert(todoEntity);
        return AppResult.success()
                .grab("id", todoEntity.getId());
    }

    @PostMapping("/delete")
    public AppResult delete(long id) {
        return AppResult.success()
                .grabAll(todoService.delete(id));
    }

    @PostMapping("/update")
    public AppResult update(@Valid TodoEntity todoEntity, Errors errors) {
        if (errors.hasErrors()) {
            return AppResult.fail("含有特殊字符");
        }
        return AppResult.success()
                .grabAll(todoService.update(todoEntity));
    }
}
