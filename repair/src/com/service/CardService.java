package com.service;

import com.dao.CardDao;
import com.dao.CardMapper;
import com.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardDao dao;

    @Autowired
    private CardMapper mapper;


    public int queryAllCardsTotal(QueryData qo, Pagination page) {
        return dao.queryAllCardsTotal(qo,page);
    }
    
    public Card querySingleCard(String cardNumber) {
        return mapper.checkCardNumberIsAlreadyExist(cardNumber);
    }

    public List<CardAttribute> queryAllCards(QueryData qo, Pagination page) {
        return dao.queryAllCards(qo,page);
    }

    public int insertCard(Card card) {
    		return mapper.insert(card);
    }

    public int updateCard(Card card) {
        return mapper.updateByPrimaryKeySelective(card);
    }

    public int delCardWithIds(List<String> delList) {
        return mapper.delCardWithIds(delList);
    }

    public int moveCardToMemberById(String cardIds, String memberId) {
        return dao.moveCardToMemberById(cardIds,memberId);
    }

    public String checkCardNumberIsAlreadyExist(String cardNumber) {
        Card card = mapper.checkCardNumberIsAlreadyExist(cardNumber);
        if(card != null){
            return "1";
        }else {
            return "0";
        }
    }

    public String getCompanyCode() {
        return mapper.getCompanyCode();
    }

    public int updateCardBalance(String cardNumber, BigDecimal balanceMoney) {
        return dao.updateCardBalance(cardNumber,balanceMoney);
    }

	public boolean checkCard(String cardno, String pwd) {
		return dao.checkCard(cardno,pwd);
	}
}
