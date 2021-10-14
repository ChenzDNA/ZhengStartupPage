package com.zheng.zhengstartuppage.model;

import com.zheng.zhengstartuppage.dao.CollectionDao;
import com.zheng.zhengstartuppage.entity.CollectionEntity;
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
public class CollectionModel {
    @Resource
    private CollectionDao collectionDao;

    public long insertCollection(CollectionEntity collectionEntity){
        collectionEntity.setUserId(SessionUtil.getUser().getId());
        collectionEntity.setCtime(new Date().getTime());
        return collectionDao.insertCollection(collectionEntity);
    }

    public void deleteCollection(long id){
        collectionDao.deleteCollection(id);
    }

    public List<CollectionEntity> getCollectionsById(long userId){
        return collectionDao.getCollectionsById(userId);
    }

    public long getCollectionUser(long id){
        return collectionDao.getCollectionUser(id);
    }
}
