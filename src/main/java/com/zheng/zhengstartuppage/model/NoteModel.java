package com.zheng.zhengstartuppage.model;

import com.zheng.zhengstartuppage.dao.NoteDao;
import com.zheng.zhengstartuppage.entity.NoteEntity;
import com.zheng.zhengstartuppage.util.SessionUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:27
 */

@Repository
public class NoteModel {
    @Resource
    private NoteDao noteDao;

    public long insertNode(NoteEntity noteEntity) {
        noteEntity.setUserId(SessionUtil.getUser().getId());
        noteEntity.setCtime(new Date().getTime());
        return noteDao.insertNote(noteEntity);
    }

    public void delete(long id) {
        noteDao.deleteNote(id);
    }

    public void update(NoteEntity noteEntity) {
        noteEntity.setMtime(new Date().getTime());
        noteDao.updateNote(noteEntity);
    }

    public List<NoteEntity> getNotesByUserId(long userId) {
        return noteDao.getNotesByUserId(userId);
    }

    public long getNoteUserIdById(long id) {
        return noteDao.getNoteById(id).getUserId();
    }

    public NoteEntity getNoteById(long id){
        return noteDao.getNoteById(id);
    }
}
