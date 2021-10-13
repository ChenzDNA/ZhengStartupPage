package com.zheng.zhengstartuppage.service;

import com.zheng.zhengstartuppage.entity.CollectionEntity;
import com.zheng.zhengstartuppage.model.CollectionModel;
import com.zheng.zhengstartuppage.util.SessionUtil;
import com.zheng.zhengstartuppage.util.returns.ReturnsData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : 陈征
 * @date : 2021-10-01 21:55
 */

@Service
public class CollectionService {
    @Resource
    private CollectionModel collectionModel;

    public long insertCollection(CollectionEntity collectionEntity) {
        collectionEntity.setUserId(SessionUtil.getUser().getId());
        return collectionModel.insertCollection(collectionEntity);
    }

    public ReturnsData deleteCollection(long id) {
        if (collectionModel.getCollectionUser(id) != SessionUtil.getUser().getId()) {
            return ReturnsData.returns("错误的用户！");
        }
        collectionModel.deleteCollection(id);
        return ReturnsData.returns(id);
    }

    public ReturnsData getCollections() {
        long userId = SessionUtil.getUser().getId();
        return ReturnsData.returns(collectionModel.getCollectionsById(userId).toArray());
    }
}
