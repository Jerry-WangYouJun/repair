package com.dao;

import com.common.Dialect;
import com.model.CardAttribute;
import com.model.Order;
import com.model.OrderAttribute;
import com.model.Pagination;
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
public class OrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<OrderAttribute> queryAllOrders(QueryData qo, Pagination page) {
        String sql = " SELECT * FROM `order` o " + whereSQL(qo)  ;
        String finalSql = Dialect.getLimitString(sql, page.getPageNo(), page.getPageSize(), "MYSQL");
        final  List<OrderAttribute> list =   new ArrayList<>();
        jdbcTemplate.query(finalSql, new RowMapper() {
            public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            		OrderAttribute order = new OrderAttribute();
                order.setOrderId(rs.getInt("order_id"));
                order.setOrderNumber(rs.getString("order_number"));
                order.setCardNumber(rs.getString("card_number"));
                order.setOrderContent(rs.getString("order_content"));
                order.setOrderDate(rs.getString("order_date"));
                order.setOrderMoney(rs.getDouble("order_money"));
                order.setDuration(rs.getString("duration"));
                order.setBrokerage(rs.getString("brokerage"));
                order.setRemark(rs.getString("remark"));
                order.setState(rs.getString("state"));
                list.add(order);
                return null ;
            }
        });
        return list;
    }

    public int queryAllOrdersTotal(QueryData qo, Pagination page) {
        final Integer[] total =  {0} ;
        String  sql  = " SELECT count(*) total FROM `order` o  " + whereSQL(qo)  ;
        jdbcTemplate.query(sql, new RowMapper() {
            public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                total[0] = rs.getInt("total");
                return null ;
            }
        });
        return total[0];
    }

    //SELECT * FROM `order`
    public String whereSQL(QueryData qo){
        String whereSql = " where 1=1 ";
        if(qo.getMbmberId() != null && qo.getMbmberId() > 0) {
        	 	 whereSql += " and o.card_number in ( select card_number from card where member_id  = '" +  qo.getMbmberId() +"')" ;
        }
        if(StringUtils.isNotEmpty(qo.getOrderState()) ) {
    		whereSql += " and   o.state  =  '" + qo.getOrderState().trim() + "' ";
    }
        if(StringUtils.isNotEmpty(qo.getSearchName()) ) {
        		whereSql += " and   o.brokerage  =  '" + qo.getSearchName().trim() + "' ";
        }
        if(StringUtils.isNotEmpty(qo.getSearchOrderNumber())){
            whereSql += " and   o.order_number  like  '%" + qo.getSearchOrderNumber().trim() + "%' ";
        }
        if(StringUtils.isNotEmpty(qo.getSearchCardNumber())){
            whereSql += " and   o.card_number  like   '%" + qo.getSearchCardNumber().trim() + "%' ";

        }
        if(StringUtils.isNotEmpty(qo.getSearchOrderContent())){
            whereSql += " and   o.order_content like  '%" + qo.getSearchOrderContent().trim() + "%' ";
        }
        if(StringUtils.isNotEmpty(qo.getSearchOrderDate()) ) {
            whereSql += " and   o.order_date  >=   '" + qo.getSearchOrderDate().trim() + "' ";
        }
        if(StringUtils.isNotEmpty(qo.getSearchOrderDateEnd()) ) {
            whereSql += " and   o.order_date   <=  '" + qo.getSearchOrderDateEnd().trim() + "' ";
        }
        return whereSql ;
    }

	public void updateOrderState(String orderNumber, String state) {
		  String updatesqlTemp = " UPDATE `order` SET state= '"+state+"' WHERE order_Number = '"+orderNumber+"'";
	       jdbcTemplate.update(updatesqlTemp);
	}
}
