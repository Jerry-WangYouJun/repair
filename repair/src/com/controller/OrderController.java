package com.controller;

import com.common.CodeUtil;
import com.model.*;
import com.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
        List<Order> list =  service.queryAllOrders(qo,page);
        int total = service.queryAllOrdersTotal(qo,page);
        Grid grid = new Grid();
        grid.setRows(list);
        grid.setTotal((long)total);
        return grid;
    }

}
