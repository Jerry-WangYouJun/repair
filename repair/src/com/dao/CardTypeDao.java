package com.dao;

import com.model.CardType;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CardTypeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CardType> queryAllCardTypes() {
        String sql = "select * from card_type " ;
        final  List<CardType> list =   new ArrayList<>();
        jdbcTemplate.query(sql, new RowMapper() {
            public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                CardType cardType = new CardType();
                cardType.setTypeId(rs.getInt("type_id"));
                cardType.setTypeName(rs.getString("type_name"));
                cardType.setTypeCode(rs.getString("type_code"));
                cardType.setBonus(rs.getDouble("bonus"));
                cardType.setRemark(rs.getString("remark"));
                list.add(cardType);
                return null ;
            }
        });
        return list;
    }

    public int queryAllCardTypesTotal() {
        final Integer[] total =  {0} ;
        String  sql  = "select count(*) total from card_type " ;
        jdbcTemplate.query(sql, new RowMapper() {
            public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                total[0] = rs.getInt("total");
                return null ;
            }
        });
        return total[0];
    }
}
