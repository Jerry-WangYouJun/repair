package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PurchaseRecordDao;
import com.dao.PurchaseRecordMapper;
import com.model.OrderAttribute;
import com.model.Pagination;
import com.model.PurchaseRecordAttribute;
import com.model.QueryData;

@Service
public class PurchaseRecordService {
    @Autowired
    private PurchaseRecordMapper mapper;

    @Autowired
    private PurchaseRecordDao dao;

    public List<PurchaseRecordAttribute> queryAllRecodes(QueryData qo, Pagination page) {
        return dao.queryAllRecords(qo,page);
    }

    public int queryAllRecodesTotal(QueryData qo, Pagination page) {
        return dao.queryAllRecordsTotal(qo,page);
    }

    public int insertConsumeRecord(OrderAttribute order,String type) {
//        PurchaseRecord record = new PurchaseRecord();
//        record.setCardNumber(order.getCardNumber());
//        record.setOrderNumber(order.getOrderNumber());
//        record.setPurchaseMoney(order.getOrderMoney());
//        record.setPurchaseType("consume");
//        record.setRemark(order.getRemark());
//        return mapper.insertSelective(record);
        return dao.insertConsumeRecord(order,type);
    }

    public int insertRechargeRecord(OrderAttribute order, String type) {
        return dao.insertRechargeRecord(order,type);
    }
}
