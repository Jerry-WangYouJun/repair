package com.controller;

import com.common.CodeUtil;
import com.model.*;
import com.service.CardTypeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/card_type")
public class CardTypeController {
    @Autowired
    private CardTypeService service;

    @RequestMapping("/card_type_query")
    public Grid cardTypeQuery(QueryData qo , HttpSession session , String pageNo , String pageSize){
        Pagination page = new Pagination(pageNo, pageSize, 100);
        CodeUtil.initPagination(page);
        List<CardType> list =  service.queryAllCardTypes();
        int total = service.queryAllCardTypesTotal();
        Grid grid = new Grid();
        grid.setRows(list);
        grid.setTotal((long)total);
        return grid;
    }


    @RequestMapping("/card_type_edit")
    public void insertUser(CardType cardType,HttpSession session, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            if ("".equals(cardType.getTypeId()) || null==cardType.getTypeId()){
                service.insertCardType(cardType);
            }else{
                service.updateCardType(cardType);
            }
            out = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("msg", "操作成功");
            json.put("success", true);
            out.println(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/card_type_delete")
    public void deletetUser(String ids,HttpServletResponse response) {
        response.setContentType("text/text;charset=UTF-8");
        PrintWriter out;
        try {
            String[] strs = ids.split(",");
            List<String> delList = new ArrayList<String>();
            for (String str : strs) {
                delList.add(str);
            }
            service.delCardTypeWithIds(delList);
            out = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("msg", "操作成功");
            json.put("success", true);
            out.println(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/getCardType")
    public void getCardType(HttpSession session, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            List<CardType> list = service.queryAllCardTypes();
            out = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("success", true);
            json.put("dataInfo", list);
            out.println(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
