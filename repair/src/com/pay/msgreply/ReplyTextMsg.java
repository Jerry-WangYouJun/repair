package com.pay.msgreply;


public class ReplyTextMsg  extends ReplyBaseMessage{
	private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
    /**
     * 将对象转换为XML
     * @return
     */
    public String Msg2Xml(){
        return "";
    }
}
