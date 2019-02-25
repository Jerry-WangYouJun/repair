package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.CodeUtil;
import com.model.Grid;
import com.model.OrderAttribute;
import com.model.Pagination;
import com.model.QueryData;
import com.service.OrderService;

@Controller
@RequestMapping("/order")
@ResponseBody
public class OrderController {

    @Autowired
    private OrderService service;

    @RequestMapping("/order_query")
    public Grid orderQuery(QueryData qo , HttpSession session ,HttpServletRequest request ){
        String pageNo = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        Pagination page = new Pagination(pageNo, pageSize, 100);
        CodeUtil.initPagination(page);
        List<OrderAttribute> list =  service.queryAllOrders(qo,page);
        int total = service.queryAllOrdersTotal(qo,page);
        Grid grid = new Grid();
        grid.setRows(list);
        grid.setTotal((long)total);
        return grid;
    }

}
