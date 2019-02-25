package com.dao;

import com.common.Dialect;
import com.model.Order;
import com.model.OrderAttribute;
import com.model.Pagination;
import com.model.PurchaseRecordAttribute;
import com.model.QueryData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PurchaseRecordDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<PurchaseRecordAttribute> queryAllRecords(QueryData qo, Pagination page) {
        String sql = " SELECT r.*,o.order_content order_content,m.name name FROM purchase_record r left JOIN `order` o on r.order_number=o.order_number " +
                " left join card c on r.card_number= c.card_number LEFT JOIN member m on c.member_id = m.member_id  " + whereSQL(qo) ;
        String finalSql = Dialect.getLimitString(sql, page.getPageNo(), page.getPageSize(), "MYSQL");
        final  List<PurchaseRecordAttribute> list =   new ArrayList<>();
        jdbcTemplate.query(finalSql, new RowMapper() {
            public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                PurchaseRecordAttribute recordAttribute = new PurchaseRecordAttribute();
                recordAttribute.setRecordId(rs.getInt("record_id"));
                recordAttribute.setOrderNumber(rs.getString("order_number"));
                recordAttribute.setCardNumber(rs.getString("card_number"));
                recordAttribute.setOrderContent(rs.getString("order_content"));
                recordAttribute.setOrderDate(rs.getDate("order_date"));
                recordAttribute.setPurchaseMoney(rs.getDouble("purchase_money"));
                recordAttribute.setName(rs.getString("name"));
                recordAttribute.setPurchaseType(rs.getString("purchase_type"));
                recordAttribute.setBalance(rs.getString("balance"));
                recordAttribute.setRemark(rs.getString("remark"));
                list.add(recordAttribute);
                return null ;
            }
        });
        return list;
    }

    public int queryAllRecordsTotal(QueryData qo, Pagination page) {
        final Integer[] total =  {0} ;
        String  sql  = " SELECT count(*) total FROM purchase_record r left JOIN `order` o on r.order_number=o.order_number left join card c on r.card_number= c.card_number " +
                " LEFT JOIN member m on c.member_id = m.member_id  " + whereSQL(qo);
        jdbcTemplate.query(sql, new RowMapper() {
            public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                total[0] = rs.getInt("total");
                return null ;
            }
        });
        return total[0];
    }

    public int insertConsumeRecord(OrderAttribute order,String type) {
        String insertsqlTemp = "INSERT INTO purchase_record (card_number,order_number,order_date,purchase_money,purchase_type,balance,remark)  "
        		+ "values ( '"+order.getCardNumber()+"','"+order.getOrderNumber()+"',now(),'"+order.getOrderMoney()+"','"+type+"' , '" + order.getMoneyBalance()+ "' ,'"+order.getRemark()+"' )";
        return jdbcTemplate.update(insertsqlTemp);
    }

    public int insertRechargeRecord(OrderAttribute order, String type) {
        String insertsqlTemp = "INSERT INTO purchase_record (card_number,order_number,order_date,purchase_money,purchase_type,balance,remark)   "
        		+ "values ( '"+order.getCardNumber()+"','"+order.getOrderNumber()+"',now(),'"+order.getOrderMoney()+"','"+type+
                "', '" + order.getMoneyBalance()+ "' ,'"+order.getRemark()+"' )";
        return jdbcTemplate.update(insertsqlTemp);
    }


    //SELECT r.*,o.order_content order_content,m.name name FROM purchase_record r left JOIN `order` o on r.order_number=o.order_number " +
    //                " left join card c on r.card_number= c.card_number LEFT JOIN member m on c.member_id = m.member_id
    public String whereSQL(QueryData qo){
        String whereSql = " where 1=1 ";
        if(StringUtils.isNotEmpty(qo.getSearchCardNumber())){
            whereSql += " and   r.card_number  like  '%" + qo.getSearchCardNumber().trim() + "%' ";

        }
        if(StringUtils.isNotEmpty(qo.getSearchName())){
            whereSql += " and   m.name like  '%" + qo.getSearchName().trim() + "%' ";
        }
        if(StringUtils.isNotEmpty(qo.getOrderTypes()) ) {
            whereSql += " and   r.purchase_type  =   '" + qo.getOrderTypes().trim() + "' ";
        }
        if(StringUtils.isNotEmpty(qo.getSearchOrderDate()) ) {
            whereSql += " and   r.order_date  =   '" + qo.getSearchOrderDate().trim() + "' ";
        }
        if(StringUtils.isNotEmpty(qo.getSearchOrderNumber()) ) {
            whereSql += " and   r.order_number  like   '%" + qo.getSearchOrderNumber().trim() + "%' ";
        }
        if(StringUtils.isNotEmpty(qo.getSearchOrderContent()) ) {
            whereSql += " and   o.order_content  like   '%" + qo.getSearchOrderContent().trim() + "%' ";
        }
        return whereSql ;
    }
}
