package com.service;

import com.dao.OrderDao;
import com.dao.OrderMapper;
import com.model.Order;
import com.model.OrderAttribute;
import com.model.Pagination;
import com.model.QueryData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper mapper;

    @Autowired
    private OrderDao dao;

    public List<OrderAttribute> queryAllOrders(QueryData qo, Pagination page) {
        return dao.queryAllOrders(qo,page);
    }

    public int queryAllOrdersTotal(QueryData qo, Pagination page) {
        return dao.queryAllOrdersTotal(qo,page);
    }


    public String checkOrderNumberIsAlreadyExist(String number) {
        String orderNumber = mapper.checkOrderNumberIsAlreadyExist(number);
        if(orderNumber != null && orderNumber!=""){
            return "1";
        }else {
            return "0";
        }
    }

    public int insertConsumeOrder(Order order) {
        return mapper.insertSelective(order);
    }

	public int insert(Order order) {
		return mapper.insertSelective(order);
	}

	public void updateOrderState(String orderNumber, String state) {
		dao.updateOrderState( orderNumber,  state);
	}
}
