package com.service;

import com.dao.CardTypeDao;
import com.dao.CardTypeMapper;
import com.model.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardTypeService {
    @Autowired
    private CardTypeDao dao;

    @Autowired
    private CardTypeMapper mapper;

    public List<CardType> queryAllCardTypes() {
        return dao.queryAllCardTypes();
    }

    public int queryAllCardTypesTotal() {
        return dao.queryAllCardTypesTotal();
    }

    public void delCardTypeWithIds(List<String> delList) {
        mapper.delCardTypeWithIds(delList);
    }

    public void insertCardType(CardType cardType) {
        mapper.insertSelective(cardType);
    }

    public void updateCardType(CardType cardType) {
        mapper.updateByPrimaryKeySelective(cardType);
    }
}
