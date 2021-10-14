package com.zheng.zhengstartuppage.service;

import ch.qos.logback.core.joran.action.AppenderRefAction;
import com.zheng.zhengstartuppage.entity.NoteEntity;
import com.zheng.zhengstartuppage.model.NoteModel;
import com.zheng.zhengstartuppage.util.SessionUtil;
import com.zheng.zhengstartuppage.util.returns.ReturnsData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : 陈征
 * @date : 2021-10-01 21:56
 */

@Service
public class NoteService {
    @Resource
    private NoteModel noteModel;

    public long insertNote(NoteEntity noteEntity) {
        noteEntity.setUserId(SessionUtil.getUser().getId());
        return noteModel.insertNode(noteEntity);
    }

    public ReturnsData delete(long id) {
        if (noteModel.getNoteUserIdById(id) != SessionUtil.getUser().getId()) {
            return ReturnsData.returns("错误的用户！");
        }
        noteModel.delete(id);
        return ReturnsData.returns(id);
    }

    public ReturnsData updateNote(NoteEntity noteEntity) {
        if (noteModel.getNoteUserIdById(noteEntity.getId()) != SessionUtil.getUser().getId()) {
            return ReturnsData.returns("错误的用户！");
        }
        noteModel.update(noteEntity);
        return ReturnsData.returns(noteEntity.getId());
    }

    public ReturnsData getNotes() {
        long userId = SessionUtil.getUser().getId();
        return ReturnsData.returns(noteModel.getNotesByUserId(userId));
    }

    public ReturnsData getNote(long id) {
        if (noteModel.getNoteUserIdById(id)!=SessionUtil.getUser().getId()){
            return ReturnsData.returns("错误的用户！");
        }
        return ReturnsData.returns(noteModel.getNoteById(id));
    }
}
