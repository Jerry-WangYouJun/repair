package com.dao;

import com.common.Dialect;
import com.model.*;
import com.service.CardService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CardDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<CardAttribute> queryAllCards(QueryData qo, Pagination page) {
        String sql = " select c.*,m.name,t.type_name from card c left join member m on c.member_id=m.member_id left join card_type t on c.card_type=t.type_id "+ whereSQL(qo) ;
        String finalSql = Dialect.getLimitString(sql, page.getPageNo(), page.getPageSize(), "MYSQL");
        final  List<CardAttribute> list =   new ArrayList<>();
        jdbcTemplate.query(finalSql, new RowMapper() {
            public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                CardAttribute cardAttribute = new CardAttribute();
                cardAttribute.setCardId(rs.getInt("card_id"));
                cardAttribute.setCardName(rs.getString("card_name"));
                cardAttribute.setCardType(rs.getInt("card_type"));
                cardAttribute.setTypeName(rs.getString("type_name"));
                cardAttribute.setCardNumber(rs.getString("card_number"));
                cardAttribute.setCardBalance(rs.getDouble("card_balance"));
                cardAttribute.setCardStatus(rs.getInt("card_status"));
                cardAttribute.setMemberId(rs.getInt("member_id"));
                cardAttribute.setMemberName(rs.getString("name"));
                cardAttribute.setRemark(rs.getString("remark"));
                list.add(cardAttribute);
                return null ;
            }
        });
        return list;
    }

    public int queryAllCardsTotal(QueryData qo, Pagination page) {
        final Integer[] total =  {0} ;
        String  sql  = "select count(*) total from card c left join member m on c.member_id=m.member_id left join card_type t on c.card_type=t.type_id " + whereSQL(qo) ;
        jdbcTemplate.query(sql, new RowMapper() {
            public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                total[0] = rs.getInt("total");
                return null ;
            }
        });
        return total[0];
    }

    public int insertCard(Card card) {
        String insertsqlTemp = "INSERT INTO card (card_name,card_type,card_number,card_balance,card_status,remark) select '"+card.getCardName()
                +"','"+card.getCardType()+"','"+card.getCardNumber()+"',bonus,'"+card.getCardStatus()+"','"+card.getRemark()
                +"' from card_type where type_id='"+card.getCardType()+"'";
        return jdbcTemplate.update(insertsqlTemp);
    }

    public int moveCardToMemberById(String cardIds, String memberId) {
        String updatesqlTemp = " UPDATE CARD SET MEMBER_ID='"+memberId+"' WHERE CARD_ID IN ("+cardIds+")";
        return jdbcTemplate.update(updatesqlTemp);
    }

    public int updateCardBalance(String cardNumber, BigDecimal balanceMoney) {
        String updatesqlTemp = " UPDATE CARD SET card_balance=+'"+balanceMoney+"' WHERE CARD_Number = '"+cardNumber+"'";
        return jdbcTemplate.update(updatesqlTemp);
    }

    //select c.*,m.name,t.type_name from card c left join member m on c.member_id=m.member_id left join card_type t on c.card_type=t.type_id
    public String whereSQL(QueryData qo){
        String whereSql = " where 1=1 ";
        if(StringUtils.isNotEmpty(qo.getSearchCardName())){
            whereSql += " and   c.card_name  like  '%" + qo.getSearchCardName().trim() + "%' ";
        }
        if(StringUtils.isNotEmpty(qo.getSearchCardNumber())){
            whereSql += " and   c.card_number  =   '" + qo.getSearchCardNumber().trim() + "' ";

        }
        if(StringUtils.isNotEmpty(qo.getSearchName())){
            whereSql += " and   m.name like  '%" + qo.getSearchName().trim() + "%' ";
        }
        if( qo.getMbmberId() != null && qo.getMbmberId() > 0 ){
            whereSql += " and   m.member_id  =   '" + qo.getMbmberId() + "' ";
        }
        if(StringUtils.isNotEmpty(qo.getCardStatuss()) ) {
            whereSql += " and   c.card_status  =   '" + qo.getCardStatuss().trim() + "' ";
        }
        if(StringUtils.isNotEmpty(qo.getCardTypes()) ) {
            whereSql += " and   c.card_type  =   '" + qo.getCardTypes().trim() + "' ";
        }
        return whereSql ;
    }

	public boolean checkCard(String cardno, String pwd) {
		String sql = "select count(*) num  from card_temp where cardno = '" + cardno + "' and pwd = '" + pwd + "' " ;
		final Integer[] total =  {0} ;
		jdbcTemplate.query(sql, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				total[0] = rs.getInt("num");
				return null ;
			}
		});
		if(total[0] > 0){
			return true;
		}else{
			return false;
		}
	}
}
