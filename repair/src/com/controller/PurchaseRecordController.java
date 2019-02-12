package com.controller;


import com.common.CodeUtil;
import com.model.*;
import com.service.OrderService;
import com.service.PurchaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/record")
@ResponseBody
public class PurchaseRecordController {

    @Autowired
    private PurchaseRecordService service;

    @RequestMapping("/record_query")
    public Grid recordQuery(QueryData qo , HttpSession session , String pageNo , String pageSize){
        Pagination page = new Pagination(pageNo, pageSize, 100);
        CodeUtil.initPagination(page);
        List<PurchaseRecordAttribute> list =  service.queryAllRecodes(qo,page);
        int total = service.queryAllRecodesTotal(qo,page);
        Grid grid = new Grid();
        grid.setRows(list);
        grid.setTotal((long)total);
        return grid;
    }
}
