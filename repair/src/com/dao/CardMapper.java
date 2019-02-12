package com.dao;

import com.model.Card;

import java.util.List;

public interface CardMapper {
    int deleteByPrimaryKey(Integer cardId);

    int insert(Card record);

    int insertSelective(Card record);

    Card selectByPrimaryKey(Integer cardId);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);

    int delCardWithIds(List<String> delList);

    Card checkCardNumberIsAlreadyExist(String cardNumber);

    String getCompanyCode();

    String checkOrderNumberIsAlreadyExist(String number);
}