package com.zheng.zhengstartuppage.controller;

import com.zheng.zhengstartuppage.entity.NoteEntity;
import com.zheng.zhengstartuppage.service.NoteService;
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
 * @date : 2021-10-14 08:11
 */

@RestController
@RequestMapping("/note")
public class NoteController {
    @Resource
    private NoteService noteService;

    @PostMapping("/insert")
    public AppResult insert(@Valid NoteEntity noteEntity, Errors errors) {
        if (errors.hasErrors()) {
            return AppResult.fail("含有特殊字符");
        }
        noteService.insertNote(noteEntity);
        return AppResult.success()
                .grab("id", noteEntity.getId());
    }

    @PostMapping("/delete")
    public AppResult delete(long id) {

        return AppResult.success()
                .grabAll(noteService.delete(id));
    }

    @PostMapping("/update")
    public AppResult update(@Valid NoteEntity noteEntity, Errors errors) {
        if (errors.hasErrors()) {
            return AppResult.fail("含有特殊字符");
        }
        return AppResult.success()
                .grabAll(noteService.updateNote(noteEntity));
    }

    @GetMapping("/getNotes")
    public AppResult getNotes(){
        return AppResult.success()
                .grabAll(noteService.getNotes());
    }

    @GetMapping("/getNote")
    public AppResult getNote(long id){
        return AppResult.success()
                .grabAll(noteService.getNote(id));
    }
}
