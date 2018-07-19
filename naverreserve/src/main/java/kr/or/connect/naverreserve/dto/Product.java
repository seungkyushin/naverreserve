package kr.or.connect.naverreserve.dto;

import java.util.Date;

public class Product {

	private int id;
	private int category_id;
	private String description;
	private String content;
	private String event;
	private String create_date;
	private String Modify_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getModify_date() {
		return Modify_date;
	}
	public void setModify_date(String modify_date) {
		Modify_date = modify_date;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", category_id=" + category_id + ", description=" + description + ", content="
				+ content + ", event=" + event + ", create_date=" + create_date + ", Modify_date=" + Modify_date + "]";
	}
	
	
	
	
}
