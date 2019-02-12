package com.pay.msg;

public class TemplateParam {
		String name ;
		String value ; 
		String color;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public TemplateParam(String name, String value, String color) {
			super();
			this.name = name;
			this.value = value;
			this.color = color;
		}
		
		

}
