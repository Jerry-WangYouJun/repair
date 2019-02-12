package com.dao;

import com.model.PurchaseRecord;

public interface PurchaseRecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(PurchaseRecord record);

    int insertSelective(PurchaseRecord record);

    PurchaseRecord selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(PurchaseRecord record);

    int updateByPrimaryKey(PurchaseRecord record);
}