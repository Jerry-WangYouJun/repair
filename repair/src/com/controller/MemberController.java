package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.CodeUtil;
import com.model.Grid;
import com.model.Member;
import com.model.MemberAttribute;
import com.model.Pagination;
import com.model.QueryData;
import com.model.User;
import com.service.MemberService;
import com.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/member")
@ResponseBody
public class MemberController {

    @Autowired
    private MemberService service;
    
    @Autowired
    private UserService userService ;


    @RequestMapping("/member_query")
    public Grid memberQuery(QueryData qo , HttpSession session , String pageNo , String pageSize){
        Pagination page = new Pagination(pageNo, pageSize, 100);
        CodeUtil.initPagination(page);
        List<MemberAttribute> list =  service.queryAllMembers(qo,page);
        int total = service.queryAllMembersTotal(qo,page);
        Grid grid = new Grid();
        grid.setRows(list);
        grid.setTotal((long)total);
        return grid;
    }


    @RequestMapping("/member_edit")
    public void insertCard(Member member,HttpSession session, HttpServletResponse response, User user ) {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            if ("".equals(member.getMemberId()) || null==member.getMemberId()){
                member.setMemberCode(getNewMemberCode());
                service.insertMember(member);
                user.setName(member.getMemberCode());
                user.setOpenid(member.getOpenId());
                user.setRoleId(member.getRole());
                userService.insertUser(user);
            }else{
                service.updateMember(member);
                user.setUsername(member.getMemberCode());
                user = userService.queryByWhere(user).get(0);
                user.setRoleId(member.getRole());
                userService.updateUser(user);
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


    @RequestMapping("/member_delete")
    public void deletetUser(String ids,HttpServletResponse response) {
        response.setContentType("text/text;charset=UTF-8");
        PrintWriter out;
        try {
            String[] strs = ids.split(",");
            List<String> delList = new ArrayList<String>();
            for (String str : strs) {
                delList.add(str);
            }
            service.delMemberWithIds(delList);
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

    public String getNewMemberCode(){
        boolean flag = true;
        String memberCode = "";
        DecimalFormat df = new DecimalFormat("00000000");
        while(flag){
           Integer code = (int)(Math.random()*10000000)-1;
           memberCode = df.format(code);
           String result = service.checkMemberCodeIsAlreadyExist(memberCode);
           if("0".equals(result)){
                flag = false;
           }
        }
        return memberCode;
    }


    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("00000000");
        for (int i = 0; i < 100; i++) {
            System.out.println(df.format((int)(Math.random()*10000000)-1));
        }
    }


}
