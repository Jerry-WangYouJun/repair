package com.pay.config;

public class WxPayConfig {
	
	/**
	 * 公众账号ID
	 */
	public static String appid = "wx074dc34770720af2";
	
	/**
	 * 
	 */
	public static String appsecret = "b10bd1342e52b960248414831d470c76";
	
	/**
	 * 商户号（mch_id）
	 */
	public static String partner = "1522866031";
	
	public static String partnerkey = "913ce023fce569f1c71bc5c0b9ae5a9a";
	
	/**
	 * 交易类型
	 */
	public static String trade_type = "JSAPI";
	
	public static String signType = "MD5"; 
	
	/**
	 * 固定套餐金额
	 * @param args
	 */
	public static  Integer money = 18 ;
	
	/**
	 * 固定套餐包id
	 * @param args
	 */
	public  static  int packageId = 1 ;
}
