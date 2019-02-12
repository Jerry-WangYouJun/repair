package com.pay.controller;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pay.config.WxPayConfig;
import com.pay.util.WXAuthUtil;


@Controller
public class IndexController {

	@RequestMapping("/xfpay")
	public String xfPay(Model model){
		
//		String rateStr = SystemConfig.getRateKey();
//		Double rate = 1.0;
//		if(StringUtils.isNotBlank(rateStr)){
//			rate = 1.0 + new Double(rateStr);
//		}
//		
//		model.addAttribute("rate", rate);
		
		return "/xfpay";
	}

}
