package com.pay.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单工具类
 * @author JeffXu
 * @since 2016-08-11
 */
public class OrderUtils {
	
	public static String genOrderNo(String iccid){
		String orderNo = "PW" + iccid + "";
		SimpleDateFormat sdf=new SimpleDateFormat("MMddhhmmss");
		String nowTime = sdf.format(new Date());
		orderNo+= nowTime;
		return orderNo;
	}

}
