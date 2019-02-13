package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.CodeUtil;
import com.model.Grid;
import com.model.Pagination;
import com.model.QueryData;
import com.model.Role;
import com.model.User;
import com.service.MemberService;
import com.service.UserService;

import net.sf.json.JSONObject;


@Controller
public class UserController {
	 
	@Autowired
	UserService service ;
	
	@RequestMapping("/checkUser")
	public String checkUser(User user , HttpServletRequest request , HttpSession session){
		user = service.checkUser(user);
		if(user.getRoleId() != null && !"003".equals(user.getRoleCode()) ){
		    session.setAttribute("user",user.getName());
		    session.setAttribute("roleid",user.getRoleId());
		    session.setAttribute("rolecode",user.getRoleCode());
			return "pages/newIndex2" ;
		}else{
			request.setAttribute("msg", "用户名或者密码错误");
			return "pages/login" ;
		}
	}
	
	@RequestMapping("/login")
	public String login(){
		return "pages/login";
	}
	
	@RequestMapping("/loginOut")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		session.removeAttribute("agentId");
        session.removeAttribute("rolecode");
		return "pages/login";
	}

	@ResponseBody
	@RequestMapping("/user/user_query")
	public Grid userQuery(QueryData qo , HttpSession session , String pageNo , String pageSize){
		Pagination page = new Pagination(pageNo, pageSize, 100);
		CodeUtil.initPagination(page);
		List<User> list =  service.queryAllUsers();
		int total = service.queryAllUsersTotal();
		Grid grid = new Grid();
		grid.setRows(list);
		grid.setTotal((long)total);
		return grid;
	}


	@RequestMapping("/user/getRoleType")
	public void getRoleType(HttpSession session, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			List<Role> list = service.getAllRoleTypes();
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



	@RequestMapping("/user/user_edit")
	public void insertUser(User user,HttpSession session, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			if ("".equals(user.getUserId()) || null==user.getUserId()){
				service.insertUser(user);
			}else{
				service.updateUser(user);
				service.updateRole(user.getOpenid() , "member" , user.getRoleId());
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

	@RequestMapping("/user/user_delete")
	public void deletetUser(String ids,HttpServletResponse response) {
		response.setContentType("text/text;charset=UTF-8");
		PrintWriter out;
		try {
			String[] strs = ids.split(",");
			List<String> delList = new ArrayList<String>();
			for (String str : strs) {
				delList.add(str);
			}
			service.delUserWithIds(delList);
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

}
