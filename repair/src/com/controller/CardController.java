package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.CodeUtil;
import com.dao.CardTypeMapper;
import com.model.Card;
import com.model.CardAttribute;
import com.model.CardType;
import com.model.Grid;
import com.model.Member;
import com.model.OrderAttribute;
import com.model.Pagination;
import com.model.QueryData;
import com.model.User;
import com.service.CardService;
import com.service.OrderService;
import com.service.PurchaseRecordService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/card")
@ResponseBody
public class CardController {

    private DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private CardService service;

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private CardTypeMapper cardTypeMapper;

    @Autowired
    private PurchaseRecordService recordService;


    @RequestMapping("/card_query")
    public Grid cardQuery(QueryData qo , HttpSession session , String pageNo , String pageSize){
        Pagination page = new Pagination(pageNo, pageSize, 100);
        CodeUtil.initPagination(page);
        List<CardAttribute> list =  service.queryAllCards(qo,page);
        int total = service.queryAllCardsTotal(qo,page);
        Grid grid = new Grid();
        grid.setRows(list);
        grid.setTotal((long)total);
        return grid;
    }


    @RequestMapping("/card_move")
    public void moveCard(String memberId,String cardIds,HttpServletResponse response) {
        response.setContentType("text/text;charset=UTF-8");
        PrintWriter out;
        try {
            service.moveCardToMemberById(cardIds,memberId);
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

    @RequestMapping("/card_edit")
    public void insertCard(Card card,HttpSession session, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            if ("".equals(card.getCardId()) || null==card.getCardId()){
                card.setCardNumber(getNewCardNumber());
                CardType type = cardTypeMapper.selectByPrimaryKey(card.getCardType());
                card.setCardBalance(type.getBonus());
                service.insertCard(card);
            }else{
                service.updateCard(card);
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

    @RequestMapping("/card_consume")
    public void cardConsume(OrderAttribute order,HttpServletResponse response,HttpSession session) {
        response.setContentType("text/text;charset=UTF-8");
        PrintWriter out;
        JSONObject json = new JSONObject();
        try {
        	    Member member = (Member)session.getAttribute("loginMember");
            order.setBrokerage(member.getName());
            //order.setOrderNumber(getNewOrderNumber(session));
            Card card = service.querySingleCard(order.getCardNumber());
            BigDecimal money = new BigDecimal(card.getCardBalance());
            money = money.subtract(new BigDecimal(order.getOrderMoney()));
            order.setState("待付款");
            orderService.insertConsumeOrder(order);
            recordService.insertConsumeRecord(order,"consume");
            service.updateCardBalance(order.getCardNumber(),money);
            out = response.getWriter();
            json.put("msg", "操作成功");
            json.put("success", true);
            out.println(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }

    @RequestMapping("/card_recharge")
    public void cardRecharge(OrderAttribute order,HttpServletResponse response,HttpSession session) {
        response.setContentType("text/text;charset=UTF-8");
        PrintWriter out;
        try {
            order.setBrokerage(session.getAttribute("user").toString());
            order.setOrderNumber(getNewOrderNumber(session));
            BigDecimal money = new BigDecimal(order.getMoneyBalance());
            money = money.add(new BigDecimal(order.getOrderMoney()));
            orderService.insertConsumeOrder(order);
            recordService.insertRechargeRecord(order,"recharge");
            service.updateCardBalance(order.getCardNumber(),money);
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
    
    @RequestMapping("/querySingle")
    public void querySingle(String cardNumber,HttpServletResponse response,HttpSession session) {
        response.setContentType("text/text;charset=UTF-8");
        PrintWriter out;
        JSONObject json = new JSONObject();
        try {
        	    Card  card = service.querySingleCard(cardNumber);
            out = response.getWriter();
            if(card != null ) {
	            	json.put("msg", "操作成功");
	            	json.put("success", true);
	            	json.put("obj", card);
            }else {
	            	json.put("msg", "操作失败，卡号不存在");
	            	json.put("success", false);
            }
            out.println(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        		json.put("msg", "操作失败，系统异常请联系管理员。异常信息：" + e.getMessage());
	        	json.put("success", false);
        }
    }


    @RequestMapping("/card_delete")
    public void deletetUser(String ids,HttpServletResponse response) {
        response.setContentType("text/text;charset=UTF-8");
        PrintWriter out;
        try {
            String[] strs = ids.split(",");
            List<String> delList = new ArrayList<String>();
            for (String str : strs) {
                delList.add(str);
            }
            service.delCardWithIds(delList);
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

    
    @RequestMapping("/checkCard")
	public void checkCard(String cardno , String pwd  ,HttpServletResponse response){
		boolean flag  = service.checkCard(cardno , pwd );
		 PrintWriter out;
			try {
				JSONObject json = new JSONObject();
				out = response.getWriter();
				if(!flag ){
					json.put("msg", "操作成功");
					json.put("success", false);
				}else{
					json.put("success", true);
				}
				out.println(json);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

    public String getNewCardNumber(){
        boolean flag = true;
        String cardNumber = "";
        DecimalFormat df = new DecimalFormat("0000");
        while(flag){
            cardNumber = dateFormat.format(new Date()).toString()+""+ df.format((int)(Math.random()*10000));
            String result = service.checkCardNumberIsAlreadyExist(cardNumber);
            if("0".equals(result)){
                flag = false;
            }
        }
        return cardNumber;
    }

    public  String getNewOrderNumber(HttpSession session){
        boolean flag = true;
        String order_subfix = service.getCompanyCode();
        String order_fubfix = "";
        DecimalFormat df = new DecimalFormat("000");
        while(flag){
            order_fubfix = dateFormat.format(new Date()).toString()+session.getAttribute("rolecode").toString()+ df.format((int)(Math.random()*1000));
            String result = orderService.checkOrderNumberIsAlreadyExist(order_subfix+order_fubfix);
            if("0".equals(result)){
                flag = false;
            }
        }
        return order_subfix+order_fubfix;
    }

    public static void main(String[] args) {
        Double d = 2.81;
        System.out.println(-d);
    }

}
