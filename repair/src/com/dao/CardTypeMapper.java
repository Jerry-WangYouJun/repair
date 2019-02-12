package com.dao;

import com.model.CardType;

import java.util.List;

public interface CardTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(CardType record);

    int insertSelective(CardType record);

    CardType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(CardType record);

    int updateByPrimaryKey(CardType record);

    int delCardTypeWithIds(List<String> delList);
}