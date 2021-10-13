package com.zheng.zhengstartuppage.controller;

import com.zheng.zhengstartuppage.entity.CollectionEntity;
import com.zheng.zhengstartuppage.service.CollectionService;
import com.zheng.zhengstartuppage.util.returns.AppResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author : 陈征
 * @date : 2021-10-13 16:29
 */

@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Resource
    private CollectionService collectionService;

    @PostMapping("/insert")
    public AppResult insert(@Valid CollectionEntity collectionEntity, Errors errors) {
        if (errors.hasErrors()) {
            return AppResult.fail("含有特殊字符");
        }
        long id = collectionService.insertCollection(collectionEntity);
        return AppResult.success()
                .grab("id", id);
    }

    @PostMapping("/delete")
    public AppResult delete(long id) {
        return AppResult.success()
                .grabAll(collectionService.deleteCollection(id));
    }

    @GetMapping("/get")
    public AppResult get() {
        return AppResult.success()
                .grabAll(collectionService.getCollections());
    }
}
