package com.model;

public class PurchaseRecordAttribute extends PurchaseRecord {

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    
    public String getContentDetail() {
		return contentDetail;
	}

	public void setContentDetail(String contentDetail) {
		this.contentDetail = contentDetail;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String orderContent;
    private String name;
    public String contentDetail;
}
